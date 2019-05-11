package org.jeecf.osgi.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.jeecf.osgi.boundle.BaseBoundle;
import org.jeecf.osgi.boundle.GenHandlerBoundle;
import org.jeecf.osgi.enums.BoundleEnum;
import org.jeecf.osgi.model.BoundleContext;
import org.jeecf.osgi.plugin.Plugin;

/**
 * 插件管理 入口
 * 
 * @author jianyiming
 *
 */
public class PluginManager {
    /**
     * 插件上下文
     */
    private Map<String, BoundleContext> boundleContextMap = new HashMap<>(12);
    /**
     * URL对应包
     */
    private Map<String, List<String>> urlMap = new HashMap<>(12);

    public PluginManager() {
        boundleContextMap.put(BoundleEnum.GEN_HANDLER_PLUGIN_BOUNDLE.getName(), new BoundleContext(new GenHandlerBoundle()));
    }

    /**
     * 安装
     * 
     * @param urls        jar文件路径集合
     * @param boundleEnum 插件枚举
     * @param isInit      是否初始化
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void install(URL[] urls, BoundleEnum boundleEnum, boolean isInit) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.install(urls, boundleEnum, isInit, null);
    }

    /**
     * 安装
     * 
     * @param urls
     * @param boundleEnum
     * @param isInit
     * @param loadder
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void install(URL[] urls, BoundleEnum boundleEnum, boolean isInit, ClassLoader loadder) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        if (urls == null || urls.length == 0) {
            return;
        }
        URLClassLoader pluginClassLoader = null;
        if (loadder != null) {
            pluginClassLoader = new URLClassLoader(urls, loadder);
        } else {
            pluginClassLoader = new URLClassLoader(urls);
        }
        BoundleContext context = boundleContextMap.get(boundleEnum.getName());
        context.setUrls(urls);
        BaseBoundle boundle = context.getBoundle();
        for (URL url : urls) {
            List<String> packageNameList = JarUtils.getClassNames(url);
            String[] packageNames = packageNameList.toArray(new String[packageNameList.size()]);
            boundle.install(pluginClassLoader, packageNames);
            urlMap.put(url.getPath(), packageNameList);
            if (isInit) {
                boundle.init(packageNames, context);
            }
        }
    }

    /**
     * 获取插件上下文
     * 
     * @param pluginEnum
     * @return
     */
    public BoundleContext getContext(BoundleEnum boundleEnum) {
        return boundleContextMap.get(boundleEnum.getName());
    }

    /**
     * 获取插件实例集合
     * 
     * @param boundleEnum
     * @return
     */
    public List<Plugin> getInstances(BoundleEnum boundleEnum) {
        BoundleContext context = boundleContextMap.get(boundleEnum.getName());
        if (CollectionUtils.isEmpty(context.getPlugins())) {
            context.setPlugins(context.getBoundle().getInstances());
        }
        return context.getPlugins();
    }

    /**
     * 获取插件实例集合
     * 
     * @param boundleEnum
     * @return
     */
    public List<Plugin> getInstances(BoundleEnum boundleEnum, URL[] urls) {
        BoundleContext context = boundleContextMap.get(boundleEnum.getName());
        BaseBoundle boundle = context.getBoundle();
        List<String> allPackageNames = new ArrayList<>();
        for (URL url : urls) {
            List<String> packageNames = urlMap.get(url.getPath());
            if (CollectionUtils.isNotEmpty(packageNames)) {
                allPackageNames.addAll(packageNames);
            }
        }
        return boundle.getInstances(allPackageNames.toArray(new String[allPackageNames.size()]));
    }

    /**
     * 获取绑定类
     * 
     * @param boundleEnum
     * @return
     */
    public BaseBoundle getBoundle(BoundleEnum boundleEnum) {
        return boundleContextMap.get(boundleEnum.getName()).getBoundle();
    }

    /**
     * 卸载
     * 
     * @param boundleEnum
     */
    public void uninstall(BoundleEnum boundleEnum) {
        boundleContextMap.get(boundleEnum.getName()).getBoundle().uninstall();
        boundleContextMap.remove(boundleEnum.getName());
    }

    /**
     * 卸载
     * 
     * @param boundleEnum
     * @param urls
     * @throws IOException
     */
    public void uninstall(BoundleEnum boundleEnum, URL url) throws IOException {
        BoundleContext context = boundleContextMap.get(boundleEnum.getName());
        List<Plugin> plugins = context.getPlugins();
        List<String> packageNames = urlMap.get(url.getPath());
        if (CollectionUtils.isNotEmpty(packageNames)) {
            List<Plugin> uninstallPlugins = context.getBoundle().uninstall(packageNames.toArray(new String[packageNames.size()]));
            if (CollectionUtils.isNotEmpty(plugins)) {
                uninstallPlugins.forEach(uninstallPlugin -> {
                    for (Plugin plugin : plugins) {
                        if (plugin == uninstallPlugin) {
                            plugins.remove(plugin);
                            break;
                        }
                    }
                });
            }
            uninstallPlugins = null;
        }
        urlMap.remove(url.getPath());
    }

}
