package org.jeecf.osgi.model;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.osgi.boundle.BaseBoundle;
import org.jeecf.osgi.plugin.Plugin;

/**
 * 插件上下文
 * 
 * @author jianyiming
 *
 */
public class BoundleContext {
    /**
     * 参数集合
     */
    private Map<String, Object> params = new HashMap<String, Object>();
    /**
     * 路径集合
     */
    private URL[] urls;
    /**
     * 插件集合
     */
    private List<Plugin> plugins;
    /**
     * 绑定类
     */
    private BaseBoundle boundle;

    public BoundleContext(BaseBoundle boundle) {
        this.boundle = boundle;
    }

    public Object getValue(String key) {
        return params.get(key);
    }

    public void setValue(String key, Object value) {
        params.put(key, value);
    }

    public URL[] getUrls() {
        return urls;
    }

    public void setUrls(URL[] urls) {
        this.urls = urls;
    }

    public List<Plugin> getPlugins() {
        return plugins;
    }

    public void setPlugins(List<Plugin> plugins) {
        this.plugins = plugins;
    }

    public BaseBoundle getBoundle() {
        return boundle;
    }

}
