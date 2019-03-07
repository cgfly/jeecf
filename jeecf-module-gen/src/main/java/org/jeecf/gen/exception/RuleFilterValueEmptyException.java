package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class RuleFilterValueEmptyException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RuleFilterValueEmptyException() {
        super("rule filter value is empty ... ");
    }

    public RuleFilterValueEmptyException(String message) {
        super(message);
    }

}
