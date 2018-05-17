package com.carme.ums.interceptor;

import com.carme.common.bo.ResponseBo;
import com.carme.common.exception.CommonException;
import com.carme.common.framework.validate.ValidateProcess;
import com.carme.common.framework.validate.common.ValidateResult;
import com.carme.common.util.JsonUtil;
import com.carme.common.util.RequestUtil;
import com.carme.ums.component.PropComponent;
import com.carme.ums.constants.UmsConstants;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Type ParamInterceptor
 * @Desc 参数验证拦截器
 * @author kelei.huang
 * @date 2012-7-3
 * @Version V1.0
 */
public class ParamInterceptor implements MethodInterceptor {

    private static Logger      log           = LoggerFactory.getLogger("request");

    private static String      modelPackPath = "com.carme.ums.vo";

    @Resource
    private HttpServletRequest request;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object controller = invocation.getThis();
        StringBuilder parseLog = new StringBuilder();
        if (controller.getClass().getAnnotation(Controller.class) != null) {
            //打印参数日志
            if (PropComponent.getProp().isRequestSwitch()) {
                parseLog.append(getRequestLog());
            }

            //检查参数
            Object checkParam = checkParam(invocation);
            if (checkParam != null) {
                return checkParam;
            }

            Object result = invocation.proceed();
            if (PropComponent.getProp().isRequestSwitch()) {
                parseLog.append("\nresult:").append(JsonUtil.toJson(result));
                log.info(parseLog.toString());
            }

            return result;
        }
        return invocation.proceed();
    }

    /**
     * 检查参数
     * @param invocation
     */
    private Object checkParam(MethodInvocation invocation) {
        Object[] paramArray = invocation.getArguments();
        for (Object param : paramArray) {
            if (param != null && isParamModel(param)) {
                //检查注释参数
                if (ValidateProcess.hasAnnotation(param.getClass())) {
                    ValidateResult checkResp = ValidateProcess.validateObject(request, param);
                    Class resultCla = invocation.getMethod().getReturnType();
                    if (!checkResp.isSuccess()) {
                        if (resultCla.equals(ResponseBo.class)) {
                            ResponseBo result = new ResponseBo<>();
                            result.setCode(ResponseBo.ERROR);
                            result.setMessage(checkResp.getErrorMsg());
                            return result;
                        } else {
                            throw new CommonException(UmsConstants.UMS_EXCEPTION.PARAM,
                                checkResp.getErrorMsg());
                        }
                    }
                }
            }
        }

        return null;
    }

    /**
     * 检查是否是model对象
     * @param param
     * @return
     */
    private boolean isParamModel(Object param) {
        if (param == null) {
            return false;
        }
        String paramPackName = param.getClass().getPackage().getName();

        if (paramPackName.indexOf(modelPackPath) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 打印日志
     */
    private String getRequestLog() {
        if (PropComponent.getProp().isRequestSwitch()) {
            return RequestUtil.getRequestMsg(request);
        }
        return "";
    }

}
