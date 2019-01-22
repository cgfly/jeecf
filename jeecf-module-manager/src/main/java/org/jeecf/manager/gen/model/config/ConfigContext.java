package org.jeecf.manager.gen.model.config;

import java.util.List;

/**
 * 配置文件config 上下文
 * 
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
    /**
     * 分发实体
     */
    private DistributionEntity distributionEntity;

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

    public DistributionEntity getDistributionEntity() {
        return distributionEntity;
    }

    public void setDistributionEntity(DistributionEntity distributionEntity) {
        this.distributionEntity = distributionEntity;
    }

}