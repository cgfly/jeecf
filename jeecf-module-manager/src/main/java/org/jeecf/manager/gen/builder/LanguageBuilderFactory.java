package org.jeecf.manager.gen.builder;

import org.jeecf.manager.common.enums.EnumUtils;
/**
 * 语言构建工厂
 * @author jianyiming
 *
 */
public class LanguageBuilderFactory {
	
	private static JavaBuilder javaBuilder = new JavaBuilder();

	private static GoBuilder goBuilder = new GoBuilder();
	
	public static AbstractLanguageBuilder getBuiler(int language) {
		if (language == EnumUtils.Language.JAVA.getCode()) {
			return javaBuilder;
		} else if (language == EnumUtils.Language.GO.getCode()) {
			return goBuilder;
		}
		return javaBuilder;
	}

}
