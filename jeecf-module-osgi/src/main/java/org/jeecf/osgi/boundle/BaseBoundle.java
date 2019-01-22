package org.jeecf.osgi.boundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.osgi.model.BoundleContext;
import org.jeecf.osgi.plugin.Plugin;

/**
 * 插件绑定类
 * 
 * @author jianyiming
 *
 * @param <R>
 */
public abstract class BaseBoundle implements LifeCycle {

    Map<String, Class<?>> classesMap = null;

    Map<String, Plugin> instancesMap = null;

    @Override
    public void install(ClassLoader loader, String[] packageNames) throws ClassNotFoundException {
        if (classesMap == null) {
            classesMap = new HashMap<>(12);
        }
        for (String packageName : packageNames) {
            Class<?> classes = loader.loadClass(packageName);
            if (getClasses().isAssignableFrom(classes)) {
                classesMap.put(packageName, classes);
            }
        }
    }

    @Override
    public void init(BoundleContext context) throws InstantiationException, IllegalAccessException {
        if (instancesMap == null) {
            instancesMap = new HashMap<>(12);
        }
        for (Map.Entry<String, Class<?>> entry : classesMap.entrySet()) {
            Plugin plugin = (Plugin) entry.getValue().newInstance();
            plugin.init(context);
            instancesMap.put(entry.getKey(), plugin);
        }
    }

    @Override
    public void init(String[] packageNames, BoundleContext context)
            throws InstantiationException, IllegalAccessException {
        if (instancesMap == null) {
            instancesMap = new HashMap<>(12);
        }
        for (String packageName : packageNames) {
            Class<?> classes = classesMap.get(packageName);
            if (classes != null) {
                Plugin plugin = (Plugin) classes.newInstance();
                plugin.init(context);
                instancesMap.put(packageName, plugin);
            }
        }
    }

    @Override
    public Plugin getInstance(String packageName) {
        return instancesMap.get(packageName);
    }

    @Override
    public List<Plugin> getInstances() {
        List<Plugin> result = new ArrayList<>();
        for (Map.Entry<String, Plugin> entry : instancesMap.entrySet()) {
            result.add(entry.getValue());
        }
        Collections.sort(result, new Comparator<Plugin>() {
            @Override
            public int compare(Plugin plugin1, Plugin plugin2) {
                return plugin1.getOrder() - plugin2.getOrder();
            }
        });
        return result;
    }

    @Override
    public void uninstall() {
        classesMap = null;
        instancesMap = null;
    }

    @Override
    public List<Plugin> uninstall(String[] packageNames) {
        List<Plugin> plugins = new ArrayList<>();
        for (String packageName : packageNames) {
            plugins.add(instancesMap.get(packageName));
            classesMap.remove(packageName);
            instancesMap.remove(packageName);
        }
        return plugins;
    }

    /**
     * 获取实现类
     * 
     * @return
     */
    public abstract Class<?> getClasses();

}
