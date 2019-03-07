package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class DistributionStrategyNotMatchException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public DistributionStrategyNotMatchException() {
        super("distribution strategy not match ... ");
    }

    public DistributionStrategyNotMatchException(String message) {
        super(message);
    }

}
