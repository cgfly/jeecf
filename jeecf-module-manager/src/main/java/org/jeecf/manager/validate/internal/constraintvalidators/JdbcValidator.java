package org.jeecf.manager.validate.internal.constraintvalidators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jeecf.manager.validate.constraints.Jdbc;

/**
 * Jdbc 连接验证器
 * 
 * @author jianyiming
 *
 */
public class JdbcValidator implements ConstraintValidator<Jdbc, CharSequence> {

    private String pattern = "jdbc:mysql://([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}:([1-9]|[1-9]\\d{3}|[1-6][0-5][0-5][0-3][0-5])/([a-zA-Z_]{1,20}|[a-zA-Z_]{1,20}[?]{1}.*)";

    @Override
    public void initialize(Jdbc constraintAnnotation) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return Pattern.matches(pattern, value);
    }

}
