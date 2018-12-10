package org.jeecf.manager.gen.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.utils.GenUtils;
import org.jeecf.manager.gen.model.ConfigContext;
import org.jeecf.manager.gen.model.ModuleEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * config 文件解析
 * 
 * @author jianyiming
 *
 */
public class ConfigResolveHandler extends AbstractHandler {

	@Override
	public void init(ChainContext context) {
		this.chainContext = context;
	}

	@Override
	public void process() {
		String sourcePath = (String) this.chainContext.get("sourcePath");
		String configPath = (String) this.chainContext.get("configPath");
		String codePath = (String) this.chainContext.get("codePath");
		ConfigContext context = new ConfigContext();
		String outDir = codePath;
		String globalParams = "";
		File configFile = new File(configPath);
		FileInputStream configIs;
		try {
			configIs = new FileInputStream(configFile);
			JsonNode node = GenUtils.getConfig(configIs);
			String outBaseDir = node.get("outBaseDir").asText();
			JsonNode globalNode = node.get("params");
			JsonNode moduleNode = node.get("module");
			if (StringUtils.isNotEmpty(outBaseDir)) {
				outDir = codePath + File.separator + outBaseDir;
			}
			if (globalNode != null) {
				globalParams = JsonMapper.toJson(globalNode);
			}
			context.setOutDir(outDir);
			context.setGlobalParams(globalParams);
			context.setModuleEntitys(this.resolveModule(moduleNode, sourcePath));
			this.chainContext.put("configContext", context);
			this.chainContext.next();
		} catch (FileNotFoundException e) {
			throw new BusinessException(SysErrorEnum.FILE_NO);
		}
	}

	/**
	 * 解析module
	 * 
	 * @param modulesNode
	 * @param basePath
	 * @return
	 */
	private List<ModuleEntity> resolveModule(JsonNode modulesNode, String basePath) {
		List<ModuleEntity> result = new ArrayList<>();
		if (modulesNode != null) {
			String name = "";
			Set<Resource> paths = null;
			String params = null;
			if (modulesNode.isArray()) {
				Iterator<JsonNode> modulesIter = modulesNode.iterator();
				while (modulesIter.hasNext()) {
					ModuleEntity context = new ModuleEntity();
					JsonNode moduleNode = modulesIter.next();
					name = resolveName(moduleNode.get("name"));
					paths = resolvePath(moduleNode.get("path"), basePath);
					params = resolveParams(moduleNode.get("params"));
					context.setName(name);
					context.setPaths(paths);
					context.setModuleParams(params);
					result.add(context);
				}
			} else {
				ModuleEntity context = new ModuleEntity();
				name = resolveName(modulesNode.get("name"));
				paths = resolvePath(modulesNode.get("path"), basePath);
				params = resolveParams(modulesNode.get("params"));
				context.setName(name);
				context.setPaths(paths);
				context.setModuleParams(params);
				result.add(context);
			}

		}
		return result;
	}
    /**
     * 解析模块 名称
     * @param nameNode
     * @return
     */
	private String resolveName(JsonNode nameNode) {
		if (nameNode != null) {
			return nameNode.asText();
		}
		throw new BusinessException(BusinessErrorEnum.CONFIG_MODULE_NAME_EMPTY);
	}

	/**
	 * 解析module path
	 * 
	 * @param pathsNode
	 * @param basePath
	 * @return
	 */
	private Set<Resource> resolvePath(JsonNode pathsNode, String basePath) {
		Set<Resource> paths = new HashSet<>();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		if (pathsNode != null) {
			try {
				if (pathsNode.isArray()) {
					Iterator<JsonNode> pathsIter = pathsNode.iterator();
					while (pathsIter.hasNext()) {
						String path = basePath + File.separator + pathsIter.next().asText();
						Resource[] resources;
						resources = resolver.getResources("file:" + path);
						for (Resource resource : resources) {
							paths.add(resource);
						}
					}
				} else {
					String path = basePath + File.separator + pathsNode.asText();
					Resource[] resources;
					resources = resolver.getResources("file:" + path);
					for (Resource resource : resources) {
						paths.add(resource);
					}
				}
				return paths;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		throw new BusinessException(BusinessErrorEnum.CONFIG_MODULE_PATH_EMPTY);
	}

	/**
	 * 解析module param
	 * 
	 * @param parmasNode
	 * @return
	 */
	private String resolveParams(JsonNode parmasNode) {
		if (parmasNode != null) {
			return JsonMapper.toJson(parmasNode);
		}
		return null;
	}

}
