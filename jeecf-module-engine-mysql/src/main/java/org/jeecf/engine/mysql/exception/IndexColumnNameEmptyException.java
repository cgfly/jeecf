package org.jeecf.engine.mysql.exception;

/**
 * 索引字段名称为空异常
 * 
 * @author jianyiming
 *
 */
public class IndexColumnNameEmptyException extends SqlEngineException {

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
