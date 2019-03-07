package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class RuleStrategyFieldEmptyException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RuleStrategyFieldEmptyException() {
        super("rule strategy field is empty ... ");
    }

    public RuleStrategyFieldEmptyException(String message) {
        super(message);
    }

}
