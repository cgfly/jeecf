package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class ConfigModulePathEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConfigModulePathEmptyException() {
        super("config module path is Empty ... ");
    }

    public ConfigModulePathEmptyException(String message) {
        super(message);
    }

}
