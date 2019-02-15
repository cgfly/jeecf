package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class DataTreeEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DataTreeEmptyException() {
        super("data tree is empty ... ");
    }

    public DataTreeEmptyException(String message) {
        super(message);
    }

}
