package org.jeecf.cache.exception;

import org.jeecf.common.exception.ThrowException;

/**
 * 缓存异常类
 * 
 * @author jianyiming
 * @version 2.0
 */
public class CacheException extends ThrowException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CacheException() {
        super("This is CacheException ...");
    }

    public CacheException(String message) {
        super(message);
    }

}
