package org.jeecf.manager.validate.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.jeecf.manager.validate.internal.constraintvalidators.EnglishValidator;

/**
 * 英文字符验证
 * 
 * @author jianyiming
 *
 */
@Documented
@Constraint(validatedBy = { EnglishValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface English {

    String message() default "{org.jeecf.validator.constraints.english.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
