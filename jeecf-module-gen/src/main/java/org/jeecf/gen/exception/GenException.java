package org.jeecf.gen.exception;

import org.jeecf.common.exception.ThrowException;

/**
 * 代码生成 异常
 * 
 * @author jianyiming
 * @version 2.0
 */
public class GenException extends ThrowException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public GenException() {
        super("This is GenException ...");
    }

    public GenException(String message) {
        super(message);
    }

}
