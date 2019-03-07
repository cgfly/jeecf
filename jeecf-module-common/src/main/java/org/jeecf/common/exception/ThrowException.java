package org.jeecf.common.exception;

/**
 * 抛出异常父类 用于统一异常处理
 * 
 * @author jianyiming
 * @version 2.0
 */
public class ThrowException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ThrowException() {
        super("this is  ThrowException ...");
    }

    public ThrowException(String message) {
        super(message);
    }

}
