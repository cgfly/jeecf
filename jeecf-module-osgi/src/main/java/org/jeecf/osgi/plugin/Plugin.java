package org.jeecf.osgi.plugin;

import org.jeecf.osgi.model.BoundleContext;
import org.jeecf.osgi.model.PluginRequest;
import org.jeecf.osgi.model.PluginResponse;

/**
 * 插件接口
 * @author jianyiming
 *
 * @param <T>
 * @param <R>
 */
public interface Plugin {
	/**
	 * 初始化
	 * @param context
	 * @return
	 */
	public void init(BoundleContext context);
	/**
	 * 插件处理
	 * @param request 入参
	 * @return PluginResponse 回参
	 */
	public PluginResponse process(PluginRequest request);
	/**
	 * 排序 用于业务编排
	 * @return
	 */
	default int getOrder() {
		return 0;
	}

}
