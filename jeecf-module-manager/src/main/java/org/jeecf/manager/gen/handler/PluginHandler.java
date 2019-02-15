package org.jeecf.manager.gen.handler;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.chain.ChainContext;
import org.jeecf.osgi.model.PluginRequest;
import org.jeecf.osgi.model.PluginResponse;
import org.jeecf.osgi.plugin.Plugin;

/**
 * 插件处理 责任链
 * 
 * @author jianyiming
 * @since 1.0
 */
public class PluginHandler extends AbstractHandler {

    private Map<String, Object> extMap = null;

    @Override
    public void init(ChainContext context) {
        super.init(context);
        extMap = this.contextParams.getExtParams();
    }

    @Override
    public void process() {
        Map<String, Object> params = this.chainContext.getParams();
        @SuppressWarnings("unchecked")
        List<Plugin> plugins = (List<Plugin>) extMap.get("genHandlerPlugin");
        Integer language = (Integer) extMap.get("language");
        String namespace = this.contextParams.getNamespaceId();
        String username = this.contextParams.getUserId();
        if (CollectionUtils.isNotEmpty(plugins)) {
            PluginRequest request = new PluginRequest();
            request.setAttribute("language", language);
            request.setAttribute("namespace", namespace);
            request.setAttribute("username", username);
            plugins.forEach(plugin -> {
                PluginResponse res = plugin.process(request);
                if (res != null) {
                    Map<String, Object> attr = res.attr();
                    for (Map.Entry<String, Object> entry : attr.entrySet()) {
                        params.put(entry.getKey(), entry.getValue());
                    }
                }
            });
        }
        this.chainContext.next();
    }

}
