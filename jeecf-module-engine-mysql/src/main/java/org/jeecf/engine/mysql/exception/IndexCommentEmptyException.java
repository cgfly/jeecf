package org.jeecf.engine.mysql.exception;

/**
 * 索引注释为空异常
 * 
 * @author jianyiming
 *
 */
public class IndexCommentEmptyException extends SqlEngineException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IndexCommentEmptyException() {
        super("idnex comment is empty... ");
    }

    public IndexCommentEmptyException(String message) {
        super(message);
    }

}
