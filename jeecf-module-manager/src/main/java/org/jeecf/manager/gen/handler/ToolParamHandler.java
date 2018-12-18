package org.jeecf.manager.gen.handler;

import java.util.Map;

import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.gen.tools.ObjectTools;
/**
 * 规则参数拦截器
 * @author jianyiming
 *
 */
public class ToolParamHandler extends AbstractHandler{

	public static ObjectTools objectTools = new ObjectTools();
	
	@Override
	public void init(ChainContext context) {
	   this.chainContext = context;
	}

	@Override
	public void process() {
		@SuppressWarnings("unchecked")
	    Map<String,Object> params = (Map<String, Object>) this.chainContext.get("params");
		params.put("ObjectTools",objectTools);
		this.chainContext.put("params",params);
		this.chainContext.next();
	}
}
