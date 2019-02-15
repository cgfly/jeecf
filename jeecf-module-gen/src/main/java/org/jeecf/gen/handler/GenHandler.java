package org.jeecf.gen.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.common.utils.XmlResolve;
import org.jeecf.common.utils.ZipUtils;
import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.enums.RuleStrategyNameEnum;
import org.jeecf.gen.model.GenSchemaTemplate;
import org.jeecf.gen.model.config.ConfigContext;
import org.jeecf.gen.model.config.ModuleEntity;
import org.jeecf.gen.model.rule.StrategyEntity;
import org.jeecf.gen.utils.GenUtils;
import org.springframework.core.io.Resource;

/**
 * 代码生成责任链
 * 
 * @author jianyiming
 * @since 1.0
 */
public class GenHandler extends AbstractHandler {

    private static final String CODE_PATH_SUFFIX = "code";

    @Override
    public void process() {
        Map<String, Object> params = this.chainContext.getParams();
        String sourcePath = contextParams.getSourcePath();
        String codePath = sourcePath + File.separator + CODE_PATH_SUFFIX;
        String outZip = contextParams.getOutZip();
        ConfigContext configContext = contextParams.getConfigContext();

        FileUtils.deleteFile(outZip);
        FileUtils.deleteDirectory(codePath);
        OutputStream out;
        try {
            List<ModuleEntity> moduleEntitys = configContext.getModuleEntitys();
            if (CollectionUtils.isNotEmpty(moduleEntitys)) {
                params.put("params", configContext.getGlobalParams());
                for (ModuleEntity moduleEntity : moduleEntitys) {
                    Set<Resource> resources = moduleEntity.getPaths();
                    String moduleParams = moduleEntity.getModuleParams();
                    params.put("moduleParams", moduleParams);
                    params.put("moduleName", moduleEntity.getName());
                    if (moduleEntity.getStrategyEntity() != null && StringUtils.isNotEmpty(moduleEntity.getStrategyEntity().getName())) {
                        builderStrategy(resources, params, configContext.getOutDir(), moduleEntity.getStrategyEntity(), moduleEntity);
                    } else {
                        params.put("data", moduleEntity.getData());
                        params.put("table", moduleEntity.getTable());
                        gen(resources, params, configContext.getOutDir());
                    }
                }
            }
            out = new FileOutputStream(new File(outZip));
            ZipUtils.toZip(codePath, out, true);
            return;
        } catch (FileNotFoundException e) {
            throw new BusinessException(SysErrorEnum.FILE_NO);
        } catch (IOException e) {
            throw new BusinessException(SysErrorEnum.IO_ERROR);
        }
    }

    /**
     * 构建策略
     * 
     * @param resources
     * @param params
     * @param ourDir
     * @param strategyEntity
     * @param moduleEntity
     * @throws IOException
     */
    public void builderStrategy(Set<Resource> resources, Map<String, Object> params, String ourDir, StrategyEntity strategyEntity, ModuleEntity moduleEntity) throws IOException {
        if (strategyEntity.getName().equals(RuleStrategyNameEnum.MANY.getName())) {
            List<Object> moduleTables = moduleEntity.getTables();
            params.put("data", moduleEntity.getData());
            for (Object table : moduleTables) {
                params.put("table", table);
                gen(resources, params, ourDir);
            }
        } else if (strategyEntity.getName().equals(RuleStrategyNameEnum.GROUP.getName())) {
            List<Map<String, Object>> moduleDatas = moduleEntity.getDatas();
            params.put("table", moduleEntity.getTable());
            for (Map<String, Object> data : moduleDatas) {
                params.put("data", data);
                gen(resources, params, ourDir);
            }
        }

    }

    /**
     * 代码生成
     * 
     * @param resources
     * @param params
     * @param ourDir
     * @throws IOException
     */
    private void gen(Set<Resource> resources, Map<String, Object> params, String ourDir) throws IOException {
        for (Resource resource : resources) {
            InputStream desIs = resource.getInputStream();
            GenSchemaTemplate genSchemaTemplate = XmlResolve.toObject(desIs, GenSchemaTemplate.class);
            GenUtils.generateToFile(genSchemaTemplate, params, ourDir);
        }
    }

}
