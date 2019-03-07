package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class RuleStrategyNameNotMatchException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RuleStrategyNameNotMatchException() {
        super("rule strategy name not match ... ");
    }

    public RuleStrategyNameNotMatchException(String message) {
        super(message);
    }

}
