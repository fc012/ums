package com.carme.common.framework.redis;

public class RedisCacheException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RedisCacheException() {
        super();
    }

    public RedisCacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public RedisCacheException(String message) {
        super(message);
    }

    public RedisCacheException(Throwable cause) {
        super(cause);
    }

}
