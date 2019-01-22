package org.jeecf.manager.engine.exception;

/**
 * 表名为空异常
 * 
 * @author jianyiming
 *
 */
public class TableNameEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TableNameEmptyException() {
        super("tableName is empty... ");
    }

    public TableNameEmptyException(String message) {
        super(message);
    }

}
