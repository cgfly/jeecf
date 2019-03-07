package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class DistributionTypeException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DistributionTypeException() {
        super("distribution type is error ... ");
    }

    public DistributionTypeException(String message) {
        super(message);
    }

}
