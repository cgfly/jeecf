package org.jeecf.manager.gen.model.config;

import java.util.List;
/**
 * 配置文件config 上下文
 * @author jianyiming
 *
 */
public class ConfigContext {
	/**
	 * 全局参数
	 */
 	private String globalParams;
 	/**
 	 * 输出路径
 	 */
	private String outDir;
	/**
	 * module 体
	 */
	private List<ModuleEntity> moduleEntitys;

	public String getGlobalParams() {
		return globalParams;
	}

	public void setGlobalParams(String globalParams) {
		this.globalParams = globalParams;
	}

	public String getOutDir() {
		return outDir;
	}

	public void setOutDir(String outDir) {
		this.outDir = outDir;
	}

	public List<ModuleEntity> getModuleEntitys() {
		return moduleEntitys;
	}

	public void setModuleEntitys(List<ModuleEntity> moduleEntitys) {
		this.moduleEntitys = moduleEntitys;
	}


}