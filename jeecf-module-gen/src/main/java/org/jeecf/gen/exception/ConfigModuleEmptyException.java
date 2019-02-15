package org.jeecf.gen.exception;

/**
 * 
 * @author jianyiming
 * @since 2.0
 *
 */
public class ConfigModuleEmptyException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public ConfigModuleEmptyException() {
        super("config module is empty... ");
    }

    public ConfigModuleEmptyException(String message) {
        super(message);
    }

}
