package org.jeecf.manager.config;

import java.nio.charset.StandardCharsets;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.MessageSourceResourceBundleLocator;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 字段验证
 * 
 * @author jianyiming
 *
 */
@Configuration
public class ValidatorConfiguration {

    @Bean
    @Primary
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
                .messageInterpolator(new ResourceBundleMessageInterpolator(new MessageSourceResourceBundleLocator(messageSource()))).buildValidatorFactory();
        return validatorFactory.getValidator();
    }

    @Bean
    public MethodValidationPostProcessor mvp() {
        return new MethodValidationPostProcessor();
    }
    
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // 如果设置为-1，表示Cache forever。一般生产环境下采用-1，开发环境为了方便调测采用某个正整数，规范地我们可通过profile来定义
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        // 设置properties文件的basename，以便找到响应的资源文件
        messageSource.setBasenames("i18n/validate/message");
        return messageSource;
    }
    
  

}
