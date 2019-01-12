package org.jeecf.manager.gen.handler;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.osgi.model.PluginRequest;
import org.jeecf.osgi.model.PluginResponse;
import org.jeecf.osgi.plugin.Plugin;

/**
 * 插件处理 责任链
 * 
 * @author jianyiming
 *
 */
public class PluginHandler extends AbstractHandler {

	@Override
	public void process() {
		System.out.println("222222222");
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) this.chainContext.get("params");
		@SuppressWarnings("unchecked")
		List<Plugin> plugins = (List<Plugin>) this.chainContext.get("genHandlerPlugin");
		System.out.println(plugins.size());
		if (CollectionUtils.isNotEmpty(plugins)) {
			PluginRequest request = new PluginRequest();
			plugins.forEach(plugin -> {
				PluginResponse res = plugin.process(request);
				if (res != null) {
					Map<String, Object> attr = res.attr();
					for (Map.Entry<String, Object> entry : attr.entrySet()) {
						System.out.println("1111111111111");
						params.put(entry.getKey(),entry.getValue());
					}
				}
			});
		}
		this.chainContext.next();
	}

}
