package org.jeecf.osgi.boundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.jeecf.osgi.model.BoundleContext;
import org.jeecf.osgi.plugin.Plugin;

/**
 * 生命周期 接口
 * 
 * @author jianyiming
 *
 */
public interface LifeCycle {

	/**
	 * 安装
	 * 
	 * @param loader       类加载器
	 * @param packageNames 包
	 * @return
	 * @throws ClassNotFoundException
	 */
	public void install(ClassLoader loader, String[] packageNames) throws ClassNotFoundException;
	
	/**
	 * 初始化
	 * @param context 上下文
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void init(BoundleContext context) throws InstantiationException, IllegalAccessException;


	/**
	 * 初始化
	 * @param packageNames 包名
	 * @param context 上下文
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void init(String[] packageNames,BoundleContext context) throws InstantiationException, IllegalAccessException;

	/**
	 * 获取实例
	 * 
	 * @param packageName
	 * @return
	 */
	public Plugin getInstance(String packageName);
     /**
      * 获取实例
      * @param packageNames 
      * @return
      */
	default List<Plugin> getInstances(String[] packageNames) {
		List<Plugin> plugins = new ArrayList<>();
		for (String packageName : packageNames) {
			Plugin plugin = getInstance(packageName);
			if (plugin != null) {
				plugins.add(plugin);
			}
		}
		Collections.sort(plugins, new Comparator<Plugin>() {
			@Override
			public int compare(Plugin plugin1, Plugin plugin2) {
				return plugin1.getOrder() - plugin2.getOrder();
			}
		});
		return plugins;
	}

	/**
	 * 获取实例集合
	 * 
	 * @return
	 */
	public List<Plugin> getInstances();

	/**
	 * 卸载
	 */
	public void uninstall();

	/**
	 * 卸载
	 * @param packageNames
	 * @return
	 */
	public List<Plugin> uninstall(String[] packageNames);

}
