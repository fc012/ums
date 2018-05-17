package com.carme.common.framework.cache;

import com.carme.common.framework.redis.RedisClient;
import com.carme.common.util.JsonUtil;
import com.carme.common.util.ObjectUtil;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Component
@Aspect
public class RedisAspect {
    @Resource
    private RedisClient redis;

    @Around("execution(* com.carme..*ServiceImpl.*(..))")
    public Object aspect(ProceedingJoinPoint pjp) {

        Method method = getMethod(pjp);
        CacheAdd cacheable = method.getAnnotation(CacheAdd.class);
        if (cacheable != null) {
            return cache(pjp, method, cacheable);
        }
        CacheDel cacheEvict = method.getAnnotation(CacheDel.class);
        if (cacheEvict != null) {
            return evict(pjp, method, cacheEvict);
        }
        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 定义缓存逻辑
     */
    public Object cache(ProceedingJoinPoint pjp, Method method, CacheAdd cacheable) {

        Object result = null;
        String fieldKey = parseKey(cacheable, method, pjp.getArgs());
        //获取方法的返回类型,让缓存可以返回正确的类型
        Class<?> returnType = ((MethodSignature) pjp.getSignature()).getReturnType();
        //使用redis 的hash进行存取，易于管理
        String resultJson = redis.hget(cacheable.key(), fieldKey);
        if ("null".equals(resultJson)) {
            return null;
        }
        if (resultJson != null) {
            if (returnType.isAssignableFrom(List.class)) {
                Type returnGeneric = method.getGenericReturnType();
                if (returnGeneric instanceof ParameterizedType) {
                    ParameterizedType pt = (ParameterizedType) returnGeneric;
                    Class<?> genericClazz = (Class<?>) pt.getActualTypeArguments()[0];
                    result = JsonUtil.fromJSONList(resultJson, genericClazz);
                } else {
                    throw new RuntimeException("返回List类型必须指定泛型");
                }
            } else {
                result = JsonUtil.fromJSON(resultJson, returnType);
            }

            return result;
        }
        try {
            result = pjp.proceed();
            Assert.notNull(fieldKey);
            redis.hset(cacheable.key(), fieldKey, JsonUtil.toJson(result), cacheable.expireTime());
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * 定义清除缓存逻辑
     * @param pjp
     * @return
     */
    public Object evict(ProceedingJoinPoint pjp, Method method, CacheDel cacheEvict) {
        try {
            String fieldKey = parseKey(cacheEvict, method, pjp.getArgs());
            redis.hdel(cacheEvict.key(), fieldKey);
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *  获取被拦截方法对象
     *
     *  MethodSignature.getMethod() 获取的是顶层接口或者父类的方法对象
     *  而缓存的注解在实现类的方法上
     *  所以应该使用反射获取当前对象的方法对象
     */
    public Method getMethod(ProceedingJoinPoint pjp) {
        Method method = null;
        try {
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            method = signature.getMethod();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return method;
    }

    /**
     *  获取缓存的key
     *  key 定义在注解上，支持SPEL表达式
     * @param pjp
     * @return
     */
    private String parseKey(Object target, Method method, Object[] args) {
        boolean needParam = true;
        String keyValue = "";
        if (target instanceof CacheAdd) {
            needParam = ((CacheAdd) target).needParam();
            keyValue = ((CacheAdd) target).fieldKey();
        } else if (target instanceof CacheDel) {
            needParam = ((CacheDel) target).needParam();
            keyValue = ((CacheDel) target).fieldKey();
        }
        if (!needParam) {
            return "";
        }

        if (StringUtils.isNotBlank(keyValue)) {
            //获取被拦截方法参数名列表(使用Spring支持类库)
            LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
            String[] paraNameArr = u.getParameterNames(method);

            //使用SPEL进行key的解析
            ExpressionParser parser = new SpelExpressionParser();
            //SPEL上下文
            StandardEvaluationContext context = new StandardEvaluationContext();
            //把方法参数放入SPEL上下文中
            for (int i = 0; i < paraNameArr.length; i++) {
                context.setVariable(paraNameArr[i], args[i]);
            }
            String[] keyArr = keyValue.split(",");
            StringBuilder keyBuild = new StringBuilder();
            for (String key : keyArr) {
                if (keyBuild.length() != 0) {
                    keyBuild.append("_");
                }
                keyBuild.append(parser.parseExpression(key).getValue(context, String.class));
            }
            return keyBuild.toString();
        } else {
            StringBuilder keyBuild = new StringBuilder();
            if (args != null) {
                for (Object arg : args) {
                    if (ObjectUtil.isBaseObject(arg)) {
                        if (keyBuild.length() != 0) {
                            keyBuild.append("_");
                        }
                        if (arg != null) {
                            keyBuild.append(arg.toString());
                        }
                    }

                }
            }
            return keyBuild.toString();
        }
    }

}
