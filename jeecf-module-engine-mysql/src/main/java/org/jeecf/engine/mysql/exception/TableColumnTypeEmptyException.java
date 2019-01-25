package org.jeecf.engine.mysql.exception;

/**
 * 表字段类型为空错误
 * 
 * @author jianyiming
 *
 */
public class TableColumnTypeEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TableColumnTypeEmptyException() {
        super("table column type is empty... ");
    }

    public TableColumnTypeEmptyException(String message) {
        super(message);
    }

}
