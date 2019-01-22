package org.jeecf.manager.engine.exception;

/**
 * 索引字段名称为空异常
 * 
 * @author jianyiming
 *
 */
public class IndexColumnNameEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public IndexColumnNameEmptyException() {
        super("idnex column name is empty... ");
    }

    public IndexColumnNameEmptyException(String message) {
        super(message);
    }

}
