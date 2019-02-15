package org.jeecf.manager.gen.handler;

import java.util.Map;

import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.manager.gen.tools.ObjectTools;
import org.jeecf.manager.gen.tools.StringTools;

/**
 * 规则参数拦截器
 * 
 * @author jianyiming
 * @since 1.0
 */
public class ToolParamHandler extends AbstractHandler {

    private static final ObjectTools OBJECT_TOOLS = new ObjectTools();

    private static final StringTools STRING_TOOLS = new StringTools();

    @Override
    public void process() {
        Map<String, Object> params = this.chainContext.getParams();
        params.put("ObjectTools", OBJECT_TOOLS);
        params.put("StringTools", STRING_TOOLS);
        this.chainContext.put("params", params);
        this.chainContext.next();
    }
}
