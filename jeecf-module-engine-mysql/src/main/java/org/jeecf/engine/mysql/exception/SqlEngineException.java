package org.jeecf.engine.mysql.exception;

import org.jeecf.common.exception.ThrowException;

/**
 * SqlEngineException
 * 
 * @author jianyiming
 * @version 2.0
 */
public class SqlEngineException extends ThrowException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SqlEngineException() {
        super("This is SqlEngineException ...");
    }

    public SqlEngineException(String message) {
        super(message);
    }

}
