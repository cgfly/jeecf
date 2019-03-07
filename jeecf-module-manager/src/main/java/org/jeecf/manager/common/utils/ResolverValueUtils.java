package org.jeecf.manager.common.utils;

import org.springframework.beans.factory.config.BeanExpressionContext;
import org.springframework.beans.factory.config.BeanExpressionResolver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.expression.StandardBeanExpressionResolver;

/**
 * 
 * @author jianyiming
 * @Ver 2.0
 */
public class ResolverValueUtils {

    private static final BeanExpressionResolver RESOLVER = new StandardBeanExpressionResolver();

    private static final String RESOLVER_PREFIX = "#{";

    private static final String RESOLVER_SUFFIX = "}";

    /**
     * 解析一个表达式，获取一个值
     * 
     * @param beanFactory
     * @param value
     * @return
     */
    public static Object resolveExpression(ConfigurableBeanFactory beanFactory, String value) {
        String resolvedValue = beanFactory.resolveEmbeddedValue(value);
        if (!(resolvedValue.startsWith(RESOLVER_PREFIX) && value.endsWith(RESOLVER_SUFFIX))) {
            return resolvedValue;
        }
        return RESOLVER.evaluate(resolvedValue, new BeanExpressionContext(beanFactory, null));
    }

}
