package org.jeecf.manager.gen.factory;

import org.jeecf.manager.common.enums.EnumUtils;
import org.jeecf.manager.gen.builder.GoBuilder;
import org.jeecf.manager.gen.builder.JavaBuilder;

/**
 * 语言构建工厂
 * 
 * @author jianyiming
 *
 */
public class LanguageFactory {

	private static JavaBuilder javaBuilder = new JavaBuilder();
	
	private static GoBuilder goBuilder = new GoBuilder();

	public static Object getTargetTable(Integer tableId, int language) {
		if (tableId == null || tableId <= 0) {
			return new Object();
		} else if (language == EnumUtils.Language.JAVA.getCode()) {
			return javaBuilder.build(tableId);
		} else if (language == EnumUtils.Language.GO.getCode()) {
			return goBuilder.build(tableId);
		}
		return  new Object();
	}

}
