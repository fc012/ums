package com.carme.common.framework.guava;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Un
 * Created by admin on 2017/5/19.
 */
public class GuavaClient {

    private static volatile byte[]          lock           = new byte[0];

    /**
     * 最大缓容量
     */
    private Integer                         maximumSize;

    /**
     * 单位秒
     */
    private Integer                         expire;

    private Cache                           cache;

    private static Map<String, GuavaClient> cacheClientMap = new ConcurrentHashMap<String, GuavaClient>();

    private GuavaClient() {

    }

    public static GuavaClient getInstance(String type, Integer expire) {
        return getInstance(type, null, expire);
    }

    /**
     * @deprecated 
     * @param type
     * @param maximumSize
     * @param expire
     * @return
     */
    public static GuavaClient getInstance(String type, Integer maximumSize, Integer expire) {
        if (maximumSize == null) {
            maximumSize = 100;
        }
        GuavaClient cacheClient = cacheClientMap.get(type);
        if (cacheClient == null) {
            synchronized (lock) {
                cacheClient = cacheClientMap.get(type);
                if (cacheClient == null) {
                    cacheClient = new GuavaClient();
                    cacheClient.setExpire(expire);
                    cacheClient.setMaximumSize(maximumSize);
                    cacheClient.init();
                    cacheClientMap.put(type, cacheClient);
                }
            }

        }
        return cacheClient;
    }

    public static GuavaClient getCache(String type) {
        return cacheClientMap.get(type);
    }

    public void init() {
        cache = CacheBuilder.newBuilder().maximumSize(maximumSize)
            .expireAfterWrite(expire, TimeUnit.SECONDS).initialCapacity(10).build();
    }

    public Object get(String key, Callable callable) throws Exception {
        return cache.get(key, callable);
    }

    public void del(String key) {
        if (cache != null) {
            cache.invalidate(key);
        }
    }

    public Integer getMaximumSize() {
        return maximumSize;
    }

    public void setMaximumSize(Integer maximumSize) {
        this.maximumSize = maximumSize;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }
}
