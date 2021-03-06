package org.jeecf.engine.mysql.exception;

/**
 * 表字段注释为空异常
 * 
 * @author jianyiming
 *
 */
public class TableColumnCommentEmptyException extends SqlEngineException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TableColumnCommentEmptyException() {
        super("table column comment is empty... ");
    }

    public TableColumnCommentEmptyException(String message) {
        super(message);
    }
}
