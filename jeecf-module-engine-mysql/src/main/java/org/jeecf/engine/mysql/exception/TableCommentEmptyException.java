package org.jeecf.engine.mysql.exception;

/**
 * 表注释为空异常
 * 
 * @author jianyiming
 *
 */
public class TableCommentEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TableCommentEmptyException() {
        super("table comment is empty... ");
    }

    public TableCommentEmptyException(String message) {
        super(message);
    }

}
