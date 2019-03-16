package org.jeecf.cache.exception;

/**
 * cache not found
 * 
 * @author jianyiming
 * @version 2.0
 *
 */
public class CacheNotFoundException extends CacheException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CacheNotFoundException() {
        super("cache not found ... ");
    }

    public CacheNotFoundException(String message) {
        super(message);
    }

}
