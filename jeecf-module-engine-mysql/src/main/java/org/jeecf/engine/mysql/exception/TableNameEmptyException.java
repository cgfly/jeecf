package org.jeecf.engine.mysql.exception;

/**
 * 表名为空异常
 * 
 * @author jianyiming
 *
 */
public class TableNameEmptyException extends SqlEngineException {

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
