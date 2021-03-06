package org.jeecf.engine.mysql.exception;

/**
 * 表字段为空异常
 * 
 * @author jianyiming
 *
 */
public class TableColumnNullException extends SqlEngineException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TableColumnNullException() {
        super("table column is null... ");
    }

    public TableColumnNullException(String message) {
        super(message);
    }

}
