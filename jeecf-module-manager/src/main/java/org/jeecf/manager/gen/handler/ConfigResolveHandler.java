package org.jeecf.manager.gen.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.gen.model.config.ConfigContext;
import org.jeecf.manager.gen.model.config.DistributionEntity;
import org.jeecf.manager.gen.resolve.ConfigDistributionResolve;
import org.jeecf.manager.gen.resolve.ConfigModuleResolve;
import org.jeecf.manager.gen.utils.GenUtils;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * config 文件解析
 * 
 * @author jianyiming
 *
 */
public class ConfigResolveHandler extends AbstractHandler {

    private ConfigModuleResolve configModuleResolve = new ConfigModuleResolve();

    private ConfigDistributionResolve configDistributionResolve = new ConfigDistributionResolve();

    @Override
    public void init(ChainContext context) {
        this.chainContext = context;
    }

    @Override
    public void process() {
        @SuppressWarnings("unchecked")
        Map<String, Object> paramsMap = (Map<String, Object>) this.chainContext.get("params");
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
            JsonNode modulesNode = node.get("module");
            JsonNode distributionNode = node.get("distribution");
            if (StringUtils.isNotEmpty(outBaseDir)) {
                outDir = codePath + File.separator + outBaseDir;
            }
            if (globalNode != null) {
                globalParams = JsonMapper.toJson(globalNode);
            }
            DistributionEntity distributionEntity = configDistributionResolve.process(distributionNode, paramsMap);
            context.setOutDir(outDir);
            context.setGlobalParams(globalParams);
            context.setModuleEntitys(configModuleResolve.process(modulesNode, sourcePath, distributionEntity.isActive()));
            context.setDistributionEntity(distributionEntity);
            this.chainContext.put("configContext", context);
            this.chainContext.next();
        } catch (FileNotFoundException e) {
            throw new BusinessException(SysErrorEnum.FILE_NO);
        }
    }

}
