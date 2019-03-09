package org.jeecf.manager.validate.internal.constraintvalidators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.jeecf.manager.validate.constraints.English;

/**
 * 英语字符 连接验证器
 * 
 * @author jianyiming
 *
 */
public class EnglishValidator implements ConstraintValidator<English, CharSequence> {

    private String pattern = "^[a-zA-Z0-9_?!@<>^%&();&~{}+-^#$.,:]+$";

    @Override
    public void initialize(English constraintAnnotation) {
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
