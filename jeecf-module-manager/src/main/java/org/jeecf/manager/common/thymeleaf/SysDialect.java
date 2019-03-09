package org.jeecf.manager.common.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * 系统方言
 * 
 * @author jianyiming
 * @version 1.0
 */
public class SysDialect extends AbstractDialect implements IExpressionObjectDialect {

    public SysDialect(String name) {
        super(name);
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return new SysIExpressionObjectFactory();
    }

}
