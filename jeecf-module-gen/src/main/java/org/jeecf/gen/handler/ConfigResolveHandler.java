package org.jeecf.gen.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.chain.ContextConfigParams;
import org.jeecf.gen.model.config.ConfigContext;
import org.jeecf.gen.model.config.DistributionEntity;
import org.jeecf.gen.resolve.ConfigDistributionResolve;
import org.jeecf.gen.resolve.ConfigModuleResolve;
import org.jeecf.gen.utils.GenUtils;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * config 文件解析
 * 
 * @author jianyiming
 * @since 1.0
 */
public class ConfigResolveHandler extends AbstractHandler {

    private static final String CONFIG_FILE_NAME = "config.json";

    private static final String CODE_PATH_SUFFIX = "code";

    @Override
    public void process() {
        Map<String, Object> paramsMap = this.chainContext.getParams();
        ContextConfigParams contextParams = this.chainContext.getContextParams();
        String sourcePath = contextParams.getSourcePath();
        String configPath = sourcePath + File.separator + CONFIG_FILE_NAME;
        String codePath = sourcePath + File.separator + CODE_PATH_SUFFIX;
        ConfigContext context = new ConfigContext();
        String outDir = codePath;
        String globalParams = "";
        String outBaseDir = "";
        File configFile = new File(configPath);
        FileInputStream configIs;
        try {
            configIs = new FileInputStream(configFile);
            JsonNode node = GenUtils.getConfig(configIs);
            JsonNode outBaseDirNode = node.get("outBaseDir");
            JsonNode globalNode = node.get("params");
            JsonNode modulesNode = node.get("module");
            JsonNode distributionNode = node.get("distribution");
            if (outBaseDirNode != null) {
                outBaseDir = outBaseDirNode.asText();
            }
            if (StringUtils.isNotEmpty(outBaseDir)) {
                outDir = codePath + File.separator + outBaseDir;
            }
            if (globalNode != null) {
                globalParams = JsonMapper.toJson(globalNode);
            }
            DistributionEntity distributionEntity = ConfigDistributionResolve.process(distributionNode, paramsMap);
            context.setOutDir(outDir);
            context.setGlobalParams(globalParams);
            context.setModuleEntitys(ConfigModuleResolve.process(modulesNode, sourcePath, distributionEntity.isActive()));
            context.setDistributionEntity(distributionEntity);
            contextParams.setConfigContext(context);
            this.chainContext.next();
        } catch (FileNotFoundException e) {
            throw new BusinessException(SysErrorEnum.FILE_NO);
        }
    }

}
