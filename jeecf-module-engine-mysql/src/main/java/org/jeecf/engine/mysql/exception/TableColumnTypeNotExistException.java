package org.jeecf.engine.mysql.exception;

/**
 * 表字段类型不存在错误
 * 
 * @author jianyiming
 *
 */
public class TableColumnTypeNotExistException extends SqlEngineException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TableColumnTypeNotExistException() {
        super("table column type not exist... ");
    }

    public TableColumnTypeNotExistException(String message) {
        super(message);
    }

}
