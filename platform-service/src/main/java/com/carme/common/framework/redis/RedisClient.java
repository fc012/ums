package com.carme.common.framework.redis;

import com.carme.common.util.JsonUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Component
@Scope("singleton")
public class RedisClient implements InitializingBean {

    protected static final Logger log       = LogManager.getLogger(RedisClient.class);

    private static JedisPool      jedisPool = null;

    @Autowired
    private JedisConfig jedisConfig;

    /**
     * 关闭
     */
    public void destroy(Jedis jedis) throws Exception {
        if (jedis != null) {
            jedis.disconnect();
        }
    }

    /**
     * 释放jedis资源
     * 
     * @param jedis
     */
    public void returnResource(Jedis redis) {
        if (redis != null) {
            redis.close();
        }
    }

    /**
     * 进行连接
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        // jedis = JedisUtil.createJedis(jedisConfig.getHost(), jedisConfig.getPort());
        if (jedisPool == null) {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(jedisConfig.getMaxActive());
            config.setMaxIdle(jedisConfig.getMaxIdle());
            config.setMaxWaitMillis(jedisConfig.getMaxWait());
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            System.out.println("IP：" + jedisConfig.getHost() + "===PORT：" + jedisConfig.getPort()
                               + "=====TIMEOUT：" + jedisConfig.getTimeout());
            jedisPool = new JedisPool(config, jedisConfig.getHost(), jedisConfig.getPort(),
                jedisConfig.getTimeout());
        }

    }

    /**
     * 获取一个值
     * 
     * @param key
     * @return
     */
    public String getKey(String key) {
        Jedis redis = null;
        try {
            redis = getJedis();
            if (redis == null) {
                return null;
            }
            return redis.get(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 设置一个值
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        set(key, value, -1);
    }

    /**
     *  一个值
     * @param key
     * @param value
     * @param expire   单位为秒
     */
    public void set(String key, String value, int expire) {
        Jedis redis = null;
        try {
            redis = getJedis();
            if (redis == null) {
                return;
            }
            redis.set(key, value);
            if (expire != -1) {
                redis.expire(key, expire);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }

    }

    /**
     * 根据key删除缓存
     * @param key
     * @return
     */
    public Long del(String key) {
        Jedis redis = null;
        try {
            redis = getJedis();
            if (redis == null) {
                return null;
            }
            return redis.del(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 添加有序set
     * @param key
     * @param value
     * @param score
     * @param expire
     */
    public void zadd(String key, String value, double score, int expire) {
        Jedis redis = null;
        try {
            redis = getJedis();
            if (redis == null) {
                return;
            }
            redis.zadd(key, score, value);
            if (expire != -1) {
                redis.expire(key, expire);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 添加有序set
     * @param key
     * @param value
     * @param score
     */
    public void zadd(String key, String value, double score) {
        zadd(key, value, score, -1);
    }

    /**
     * 倒序排序获得set
     * @param key
     * @param limit
     * @return
     */
    public List<String> zrevrange(String key, int limit) {
        Jedis redis = null;
        try {
            List<String> resultList = new ArrayList<String>();
            redis = getJedis();
            if (redis == null) {
                return resultList;
            }
            Set<String> set = redis.zrevrange(key, 0, limit - 1);
            Iterator<String> iter = set.iterator();

            while (iter.hasNext()) {
                resultList.add(iter.next());
            }
            return resultList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ArrayList<String>();
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 把对象放入Hash中
     * @param key
     * @param field
     * @param o
     */
    public void hset(String key, String field, String value, int expireSeconds) {
        Jedis redis = null;
        try {
            redis = getJedis();
            if (redis == null) {
                return;
            }
            redis.hset(key, field, value);
            if (expireSeconds != -1) {
                redis.expire(key, expireSeconds);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 把对象放入Hash中
     * @param key
     * @param field
     * @param o
     */
    public void hset(String key, String field, String value) {
        hset(key, field, value, -1);
    }

    /**
     * 从Hash中获取对象
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field) {
        Jedis redis = null;
        try {
            redis = getJedis();
            if (redis == null) {
                return null;
            }
            return redis.hget(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 从Hash中获取对象,转换成制定类型
     * @param key
     * @param field
     * @param clazz
     * @return
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     */
    public <T> T hget(String key, String field, Class<T> clazz) {
        String text = hget(key, field);
        if (StringUtils.isBlank(text)) {
            return null;
        }
        T result = JsonUtil.fromJSON(text, clazz);
        return result;
    }

    public <T> List<T> hgetList(String key, String field, Class<T> clazz) {
        String text = hget(key, field);
        if (StringUtils.isBlank(text)) {
            return null;
        }
        List<T> result = JsonUtil.fromJSONList(text, clazz);
        return result;
    }

    /**
     * 从Hash中删除对象
     * @param key
     * @param field
     * @return
     */
    public Long hdel(String key, String... field) {
        Jedis redis = null;
        try {
            redis = getJedis();
            return redis.hdel(key, field);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0l;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 将一个值插入到列表头部，value可以重复，返回列表的长度
     * @param key
     * @param value String
     * @return 返回List的长度
     */
    public Long lpush(String key, String value) {
        Jedis redis = null;
        try {
            redis = getJedis();
            Long length = redis.lpush(key, value);
            return length;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 从列表尾部取出一个值
     * @param key    
     * @return 返回value
     */
    public String rpop(String key) {
        Jedis redis = null;
        try {
            redis = getJedis();
            String value = redis.rpop(key);
            return value;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 移除列表元素，返回移除的元素数量
     * @param key
     * @param value 匹配的元素
     * @return Long
     */
    public Long lrem(String key, String value) {
        return lrem(key, 0, value);
    }

    /**
     * 移除列表元素，返回移除的元素数量
     * @param key
     * @param count，标识，表示动作或者查找方向
     * <li>当count=0时，移除所有匹配的元素；</li>
     * <li>当count为负数时，移除方向是从尾到头；</li>
     * <li>当count为正数时，移除方向是从头到尾；</li>
     * @param value 匹配的元素
     * @return Long
     */
    public Long lrem(String key, long count, String value) {
        Jedis redis = null;
        try {
            redis = getJedis();
            Long length = redis.lrem(key, count, value);
            return length;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 获取List列表
     * @param key
     * @param start long，开始索引
     * @param end long， 结束索引
     * @return List<String>
     */
    public List<String> lrange(String key, long start, long end) {
        Jedis redis = null;
        try {
            redis = getJedis();
            List<String> list = redis.lrange(key, start, end);
            return list;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ArrayList<String>();
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 通过索引获取列表中的元素
     * @param key
     * @param index，索引，0表示最新的一个元素
     * @return String
     */
    public String lindex(String key, long index) {
        Jedis redis = null;
        try {
            redis = getJedis();
            String value = redis.lindex(key, index);
            return value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "";
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 获取列表长度，key为空时返回0
     * @param key
     * @return Long
     */
    public Long llen(String key) {
        Jedis redis = null;
        try {
            redis = getJedis();
            Long length = redis.llen(key);
            return length;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return 0l;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 获取Jedis实例
     * 
     * @return
     */
    public synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public boolean setnx(String key, String value, int expire) {
        Jedis redis = null;
        try {
            redis = getJedis();
            boolean result = (redis.setnx(key, value) == 1);
            if (result && expire != -1) {
                redis.expire(key, expire);
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 分布式锁
     * @param key
     * @param timeout 超时时间,锁等待超时，防止线程饥饿，永远没有入锁执行代码的机会 (毫秒)
     * @param expire 锁持有超时，防止线程在入锁以后，无限的执行下去，让锁无法释放(毫秒)
     * @return
     * @throws InterruptedException
     */
    public boolean getLock(String key, int timeout, int expire) throws InterruptedException {

        Jedis redis = null;
        try {
            redis = getJedis();
            if (timeout < 0) {
                timeout = 0;
            }
            boolean flag = false;
            while (timeout >= 0) {
                long expires = System.currentTimeMillis() + expire + 1;
                String expiresStr = String.valueOf(expires); //锁到期时间
                if (redis.setnx(key, expiresStr) == 1) {
                    flag = true;
                    break;
                }
                String currentValueStr = redis.get(key); //redis里的时间
                if (currentValueStr != null
                    && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
                    redis.del(key);
                    flag = true;
                    break;
                }

                timeout -= 100;
                Thread.sleep(100);
            }
            return flag;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        } finally {
            returnResource(redis);
        }
    }

    /**
     * 获取key的超时时间
     * @param key
     * @return
     */
    public Long getTimeOut(String key) {
        Jedis redis = null;
        try {
            redis = getJedis();
            Long timeOunt = redis.ttl(key);
            return timeOunt;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return -2L;
        } finally {
            returnResource(redis);
        }
    }

}
