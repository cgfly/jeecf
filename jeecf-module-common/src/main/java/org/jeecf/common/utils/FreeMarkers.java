/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package org.jeecf.common.utils;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * FreeMarkers工具类  使用jeesite相关实现
 * 
 * @author ThinkGem
 * @version 2013-01-15
 */
public class FreeMarkers {

	public static String renderString(String name,String templateString, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template(name, new StringReader(templateString),
					new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS));
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new BusinessException(SysErrorEnum.NOT_CHECK_ERROR);
		}
	}

	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new BusinessException(SysErrorEnum.NOT_CHECK_ERROR);
		}
	}

	public static Configuration buildConfiguration(String directory) throws IOException {
		Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}

	

}
