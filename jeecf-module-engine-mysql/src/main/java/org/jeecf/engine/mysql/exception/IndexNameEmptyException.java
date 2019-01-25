package org.jeecf.engine.mysql.exception;

/**
 * 索引名称为空 异常
 * 
 * @author jianyiming
 *
 */
public class IndexNameEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IndexNameEmptyException() {
        super("idnex name is empty... ");
    }

    public IndexNameEmptyException(String message) {
        super(message);
    }

}
