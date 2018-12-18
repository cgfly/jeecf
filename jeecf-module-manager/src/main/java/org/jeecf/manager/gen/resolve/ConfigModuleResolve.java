package org.jeecf.manager.gen.resolve;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.gen.model.config.ModuleEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * config module 解析
 * @author jianyiming
 *
 */
public class ConfigModuleResolve {
	
	public List<ModuleEntity> process(JsonNode modulesNode,String sourcePath) {
		List<ModuleEntity> mdouleEntityList = new ArrayList<>();
		if (modulesNode != null) {
			if (modulesNode.isArray()) {
				Iterator<JsonNode> modulesIter = modulesNode.iterator();
				while (modulesIter.hasNext()) {
					JsonNode moduleNode = modulesIter.next();
					mdouleEntityList.add(this.buildModuleEntity(moduleNode, sourcePath));
				}
			} else {
				mdouleEntityList.add(this.buildModuleEntity(modulesNode, sourcePath));
			}
		} else {
			throw new BusinessException(BusinessErrorEnum.CONFIG_MODULE_MPTY);
		}
		return mdouleEntityList;
	}
	
	/**
	 * 解析模块 名称
	 * 
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
						this.buildPathResources(path, resolver, paths);
					}
				} else {
					String path = basePath + File.separator + pathsNode.asText();
					this.buildPathResources(path, resolver, paths);
				}
				return paths;
			} catch (IOException e) {
				throw new BusinessException(SysErrorEnum.IO_ERROR);
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
	/**
	 * 解析module rule
	 * @param ruleNode
	 * @return
	 */
	private String resolveRule(JsonNode ruleNode) {
		if (ruleNode != null) {
			return ruleNode.asText();
		}
		return "default";
	}
	/**
	 * 构建ModuleEntity
	 * @param moduleNode
	 * @param path
	 * @return
	 */
	private  ModuleEntity buildModuleEntity(JsonNode moduleNode,String path ) {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.setName(resolveName(moduleNode.get("name")));
		moduleEntity.setRule(resolveRule(moduleNode.get("rule")));
		moduleEntity.setPaths(resolvePath(moduleNode.get("path"), path));
		moduleEntity.setModuleParams(resolveParams(moduleNode.get("params")));
		return moduleEntity;
	}
	
	/**
	 * 构建 PathResources
	 * @param path
	 * @param resolver
	 * @param paths
	 * @throws IOException
	 */
	private  void buildPathResources(String path,ResourcePatternResolver resolver,Set<Resource> paths) throws IOException {
		Resource[] resources;
		resources = resolver.getResources("file:" + path);
		for (Resource resource : resources) {
			paths.add(resource);
		}
	}

}
