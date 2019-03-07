package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class TableManyEmptyException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TableManyEmptyException() {
        super("table many is empty ... ");
    }

    public TableManyEmptyException(String message) {
        super(message);
    }

}
