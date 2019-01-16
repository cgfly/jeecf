package org.jeecf.manager.common.thymeleaf;

import java.util.HashMap;
import java.util.Map;

import org.jeecf.manager.common.thymeleaf.expression.Dbsources;
import org.jeecf.manager.common.thymeleaf.expression.Namespaces;
import org.jeecf.manager.common.thymeleaf.expression.Users;
import org.thymeleaf.context.IProcessingContext;
import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionEnhancingDialect;

/**
 * thymeleaf 方言
 * @author jianyiming
 *
 */
public class SysDialect extends AbstractDialect implements IExpressionEnhancingDialect{

	private static final Map<String,Object> EXPRESSION_MAP = initExpressionMap() ;
	
//	private static final String ENUM_NAME = "enums";
	
	private static final String USER_NAME = "users";
	
	private static final String NAMESPACE_NAME = "namespaces";
	
	private static final String DBSOURCE_NAME = "dbsources";
	
	private static Map<String,Object> initExpressionMap(){
		Map<String,Object> expressionMap = new HashMap<String,Object>(10);
//		expressionMap.put(ENUM_NAME, new Enums());
		expressionMap.put(USER_NAME, new Users());
		expressionMap.put(NAMESPACE_NAME, new Namespaces());
		expressionMap.put(DBSOURCE_NAME, new Dbsources());
		return expressionMap;
	}
	
	@Override
	public String getPrefix() {
		return null;
	}

	@Override
	public Map<String, Object> getAdditionalExpressionObjects(IProcessingContext processingContext) {
		return EXPRESSION_MAP;
	}

}
