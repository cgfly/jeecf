package org.jeecf.manager.common.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.jeecf.manager.common.thymeleaf.expression.Dbsources;
import org.jeecf.manager.common.thymeleaf.expression.Namespaces;
import org.jeecf.manager.common.thymeleaf.expression.Users;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

/**
 * thymeleaf 方言表达式工厂
 * 
 * @author jianyiming
 * @version 2.0
 */
public class SysIExpressionObjectFactory implements IExpressionObjectFactory {

    private static final Set<String> EXPRESSION_SET = initExpressionSet();

    private static final String USER_NAME = "users";

    private static final String NAMESPACE_NAME = "namespaces";

    private static final String DBSOURCE_NAME = "dbsources";

    private static Set<String> initExpressionSet() {
        Set<String> expressionSet = new HashSet<>();
        expressionSet.add(USER_NAME);
        expressionSet.add(NAMESPACE_NAME);
        expressionSet.add(DBSOURCE_NAME);
        return expressionSet;
    }

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return EXPRESSION_SET;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        if (null == expressionObjectName)
            return null;
        Object obj = null;
        switch (expressionObjectName) {
        case USER_NAME:
            obj = new Users();
            break;
        case NAMESPACE_NAME:
            obj = new Namespaces();
            break;
        case DBSOURCE_NAME:
            obj = new Dbsources();
            break;
        default:
            break;
        }
        return obj;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return true;
    }

}
