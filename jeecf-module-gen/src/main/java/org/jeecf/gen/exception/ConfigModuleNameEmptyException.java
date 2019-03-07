package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class ConfigModuleNameEmptyException extends GenException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConfigModuleNameEmptyException() {
        super("config module name is empty... ");
    }

    public ConfigModuleNameEmptyException(String message) {
        super(message);
    }

}
