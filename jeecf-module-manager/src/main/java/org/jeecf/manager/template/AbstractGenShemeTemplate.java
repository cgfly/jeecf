package org.jeecf.manager.template;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.utils.IdGenUtils;
import org.jeecf.manager.common.model.GenEntity;
import org.jeecf.manager.common.properties.TemplateProperties;
import org.jeecf.manager.common.utils.GenUtils;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.template.model.GenSchemaTemplate;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 抽象模版方法 用于代码生成
 * 
 * @author jianyiming
 *
 * @param <V>
 */
public abstract class AbstractGenShemeTemplate<V extends GenEntity> {

	/**
	 * 配置文件名称
	 */
	private static final String CONFIG_NAME = "config.json";

	/**
	 * 配置文件路径前缀
	 */
	private static final String CONFIG_PERFIX = "/template/gen/";

	public String create(V entity) {
		String pathName = AbstractGenShemeTemplate.CONFIG_PERFIX + AbstractGenShemeTemplate.CONFIG_NAME;
		Iterator<Entry<String, JsonNode>> nodes = GenUtils.getConfig(pathName, getType());
		String randomPath = IdGenUtils.randomUUID(4);
	    TemplateProperties properties = SpringContextUtils.getBean(TemplateProperties.class);
		String basePath = properties.getDownloadPerfixPath() + File.separator + randomPath;
		while (nodes.hasNext()) {
			Entry<String, JsonNode> node = nodes.next();
			pathName = AbstractGenShemeTemplate.CONFIG_PERFIX + node.getValue().asText();
			GenSchemaTemplate genTemplate = GenUtils.xmlToObject(pathName, GenSchemaTemplate.class);

			Map<String, Object> model = new HashMap<String, Object>(20);
			model.put("packageName", StringUtils.lowerCase(entity.getPackageName()));
			model.put("functionVersion", entity.getFunctionVersion());
			model.put("lastPackageName", StringUtils.substringAfterLast((String) model.get("packageName"), "."));
			model.put("className", StringUtils.uncapitalize(entity.getClassName()));
			model.put("ClassName", StringUtils.capitalize(entity.getClassName()));
			model.put("functionName", entity.getFunctionName());
			model.put("functionAuthor", entity.getFunctionAuthor());
			initParams(model, entity);
			GenUtils.generateToFile(genTemplate, model, basePath);
		}
		return randomPath;
	}

	/**
	 * 获取类型
	 * @return
	 */
	public abstract String getType();

	/**
	 * 自定义参数
	 * 
	 * @param map
	 * @param v
	 */
	public abstract void initParams(Map<String, Object> map, V v);

}
