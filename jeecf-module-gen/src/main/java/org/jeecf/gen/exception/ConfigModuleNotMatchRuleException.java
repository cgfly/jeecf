package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 *
 */
public class ConfigModuleNotMatchRuleException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConfigModuleNotMatchRuleException() {
        super("config module not match rule ... ");
    }

    public ConfigModuleNotMatchRuleException(String message) {
        super(message);
    }

}
