package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class RuleStrategyNameException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RuleStrategyNameException() {
        super("rule strategy name is error ... ");
    }

    public RuleStrategyNameException(String message) {
        super(message);
    }

}
