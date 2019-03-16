package org.jeecf.manager.module.extend.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.cache.annotation.FlushCache;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.service.NamespaceAuthService;
import org.jeecf.manager.common.utils.PluginUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.extend.dao.SysOsgiPluginDao;
import org.jeecf.manager.module.extend.model.domain.SysOsgiPlugin;
import org.jeecf.manager.module.extend.model.po.SysOsgiPluginPO;
import org.jeecf.manager.module.extend.model.query.SysOsgiPluginQuery;
import org.jeecf.manager.module.extend.model.result.SysOsgiPluginResult;
import org.jeecf.osgi.enums.BoundleEnum;
import org.jeecf.osgi.plugin.Plugin;
import org.jeecf.osgi.utils.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import lombok.extern.slf4j.Slf4j;

/**
 * OSGI 插件service
 * 
 * @author jianyiming
 *
 */
@Service
@Slf4j
public class SysOsgiPluginService extends NamespaceAuthService<SysOsgiPluginDao, SysOsgiPluginPO, SysOsgiPluginResult, SysOsgiPluginQuery, SysOsgiPlugin> {

    @Autowired
    private PluginManager pluginManager;

    @Autowired
    private ConfigurableWebApplicationContext applicationContext;

    public void initPlugin() {
        try {
            SysOsgiPluginQuery query = new SysOsgiPluginQuery();
            Response<List<SysOsgiPluginResult>> sysOsgiPluginRes = this.findList(new SysOsgiPluginPO(query));
            List<URL> genHandlerURL = new ArrayList<>();
            if (sysOsgiPluginRes.isSuccess() && CollectionUtils.isNotEmpty(sysOsgiPluginRes.getData())) {
                List<SysOsgiPluginResult> sysOsgiPlugins = sysOsgiPluginRes.getData();
                for (SysOsgiPluginResult sysOsgiPlugin : sysOsgiPlugins) {
                    if (sysOsgiPlugin.getBoundleType() == BoundleEnum.GEN_HANDLER_PLUGIN_BOUNDLE.getCode()) {
                        URL url = new URL("file:" + PluginUtils.getFilePath(sysOsgiPlugin.getName()));
                        genHandlerURL.add(url);
                    }
                }
                pluginManager.install(genHandlerURL.toArray(new URL[genHandlerURL.size()]), BoundleEnum.GEN_HANDLER_PLUGIN_BOUNDLE, true, applicationContext.getClassLoader());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<SysOsgiPluginResult> insert(SysOsgiPlugin sysOsgiPlugin) {

        try {
            Response<SysOsgiPluginResult> sysOsgiPluginResultRes = super.insert(sysOsgiPlugin);
            boolean flag = FileUtils.copyFileCover(PluginUtils.getTmpFilePath(sysOsgiPlugin.getName()), PluginUtils.getFilePath(sysOsgiPlugin.getName()), true);
            if (flag) {
                pluginManager.install(new URL[] { new URL("file:" + PluginUtils.getFilePath(sysOsgiPlugin.getName())) }, BoundleEnum.GEN_HANDLER_PLUGIN_BOUNDLE, true,
                        applicationContext.getClassLoader());
                return sysOsgiPluginResultRes;
            }
            throw new BusinessException(SysErrorEnum.IO_ERROR);
        } catch (Exception e) {
            PluginUtils.delFile(sysOsgiPlugin.getName());
            e.printStackTrace();
            throw new BusinessException(SysErrorEnum.SYSTEM_ERROR.getCode(), e.getMessage());
        } finally {
            PluginUtils.delTmpFile(sysOsgiPlugin.getName());
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> delete(SysOsgiPlugin sysOsgiPlugin) {
        Response<SysOsgiPluginResult> sysOsgiPluginRes = super.get(sysOsgiPlugin);
        if (sysOsgiPluginRes.isSuccess()) {
            SysOsgiPluginResult sysOsgiPluginResult = sysOsgiPluginRes.getData();
            sysOsgiPlugin.setName(sysOsgiPluginResult.getName());
            sysOsgiPlugin.setId(null);
            Response<Integer> response = super.delete(sysOsgiPlugin);
            String path = PluginUtils.getFilePath(sysOsgiPluginResult.getName());
            try {
                pluginManager.uninstall(BoundleEnum.GEN_HANDLER_PLUGIN_BOUNDLE, new URL("file:" + path));
                PluginUtils.delFile(sysOsgiPluginResult.getName());
                return response;
            } catch (Exception e) {
                throw new BusinessException(SysErrorEnum.SYSTEM_ERROR.getCode(), e.getMessage());
            }
        }
        throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
    }

    @FlushCache
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> updateByName(SysOsgiPlugin sysOsgiPlugin) {
        sysOsgiPlugin.setUpdateBy(UserUtils.getCurrentUserId());
        sysOsgiPlugin.setUpdateDate(new Date());
        return new Response<>(dao.updateByName(sysOsgiPlugin));
    }

    public Response<List<Plugin>> findFilePathByBoundleType(BoundleEnum boundleEnum) {
        List<URL> urls = new ArrayList<>();
        SysOsgiPluginQuery query = new SysOsgiPluginQuery();
        query.setBoundleType(boundleEnum.getCode());
        List<SysOsgiPluginResult> sysOsgiPluginList = this.findListByAuth(new SysOsgiPluginPO(query)).getData();
        if (CollectionUtils.isNotEmpty(sysOsgiPluginList)) {
            sysOsgiPluginList.forEach(sysOsgiPlugin -> {
                try {
                    URL url = new URL("file:" + PluginUtils.getFilePath(sysOsgiPlugin.getName()));
                    urls.add(url);
                } catch (MalformedURLException e) {
                    throw new BusinessException(SysErrorEnum.SYSTEM_ERROR.getCode(), e.getMessage());
                }
            });
        }
        return new Response<>(pluginManager.getInstances(BoundleEnum.GEN_HANDLER_PLUGIN_BOUNDLE, urls.toArray(new URL[urls.size()])));
    }

}
