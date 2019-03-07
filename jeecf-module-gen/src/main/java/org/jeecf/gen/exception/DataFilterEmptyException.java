package org.jeecf.gen.exception;
/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class DataFilterEmptyException extends GenException {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DataFilterEmptyException() {
        super("data filter is empty ... ");
    }

    public DataFilterEmptyException(String message) {
        super(message);
    }

}
