package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class DataGroupEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DataGroupEmptyException() {
        super("data group is empty ... ");
    }

    public DataGroupEmptyException(String message) {
        super(message);
    }

}
