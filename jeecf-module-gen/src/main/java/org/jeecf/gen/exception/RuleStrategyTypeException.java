package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class RuleStrategyTypeException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RuleStrategyTypeException() {
        super("rule strategy type is error ... ");
    }

    public RuleStrategyTypeException(String message) {
        super(message);
    }

}
