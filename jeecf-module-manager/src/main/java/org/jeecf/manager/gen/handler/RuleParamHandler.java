package org.jeecf.manager.gen.handler;

import java.util.Map;

import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.gen.rule.ObjectRule;
/**
 * 规则参数拦截器
 * @author jianyiming
 *
 */
public class RuleParamHandler extends AbstractHandler{

	public static ObjectRule objectRule = new ObjectRule();
	
	@Override
	public void init(ChainContext context) {
	   this.chainContext = context;
	}

	@Override
	public void process() {
		@SuppressWarnings("unchecked")
	    Map<String,Object> params = (Map<String, Object>) this.chainContext.get("params");
		params.put("ObjectRule",objectRule);
		this.chainContext.put("params",params);
		this.chainContext.next();
	}
}
