package org.jeecf.manager.gen.model;

import java.util.Set;

import org.springframework.core.io.Resource;

/**
 * config module 参数体
 * 
 * @author jianyiming
 *
 */
public class ModuleEntity {
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 模块参数
	 */
	private String moduleParams;
	/**
	 * 路径集合
	 */
	private Set<Resource> paths;

	public String getModuleParams() {
		return moduleParams;
	}

	public void setModuleParams(String moduleParams) {
		this.moduleParams = moduleParams;
	}

	public Set<Resource> getPaths() {
		return paths;
	}

	public void setPaths(Set<Resource> paths) {
		this.paths = paths;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
