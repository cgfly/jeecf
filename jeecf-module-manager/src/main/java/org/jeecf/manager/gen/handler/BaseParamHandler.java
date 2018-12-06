package org.jeecf.manager.gen.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jeecf.common.utils.DateFormatUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;

/**
 * 基础参数责任链
 * @author jianyiming
 *
 */
public class BaseParamHandler extends AbstractHandler{
	

	@Override
	public void init(ChainContext context) {
	   this.chainContext = context;
	}

	@Override
	public void process() {
		@SuppressWarnings("unchecked")
		Map<String,Object> params = (Map<String, Object>) this.chainContext.get("params");
		if(params == null) {
			params = new HashMap<String,Object>();
		}
		params.put("nowDate", DateFormatUtils.SF.format(new Date()));
		this.chainContext.put("params",params);
		this.chainContext.next();
	}

}
