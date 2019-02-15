package org.jeecf.manager.gen.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.jeecf.gen.chain.ChainContext;
import org.jeecf.gen.chain.ContextConfigParams;
import org.jeecf.gen.model.GenParams;
import org.jeecf.manager.gen.hook.TableHookImpl;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.osgi.plugin.Plugin;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class GenUtils {

    private static final ChainTemplateImpl CHAIN_TEMPLATE_IMPL = initTemplate();

    /**
     * 初始化代码生成责任链
     * 
     * @return
     */
    private static final synchronized ChainTemplateImpl initTemplate() {
        ChainTemplateImpl chainTemplateImpl = new ChainTemplateImpl();
        chainTemplateImpl.initChainContext(new TableHookImpl());
        return chainTemplateImpl;
    }

    public static String build(List<GenParams> genParamsList, String tableName, String sourcePath, Integer language, SysNamespace sysNamespace, SysUser sysUser, List<Plugin> plugins) {
        ChainContext genChainContext = CHAIN_TEMPLATE_IMPL.getChainContext();
        String outZip = sourcePath + File.separator + "code.zip";
        HashMap<String, Object> extMap = new HashMap<>(8);
        ContextConfigParams contextParams = new ContextConfigParams();
        contextParams.setNamespaceId(sysNamespace.getId());
        contextParams.setUserId(sysUser.getId());
        contextParams.setSourcePath(sourcePath);
        contextParams.setTableName(tableName);
        contextParams.setOutZip(outZip);
        extMap.put("genParamsList", genParamsList);
        extMap.put("genHandlerPlugin", plugins);
        extMap.put("language", language);
        contextParams.setExtParams(extMap);
        try {
            genChainContext.setContextParams(contextParams);
            genChainContext.next();
            if (genChainContext.isFlag()) {
                return outZip;
            }
        } finally {
            genChainContext.remove();
        }
        return null;
    }

}
