package org.jeecf.manager.gen.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.utils.DateFormatUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.gen.model.GenParams;

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
		@SuppressWarnings("unchecked")
		List<GenParams> genParamsList = (List<GenParams>) this.chainContext.get("genParamsList");
		params.put("nowDate", DateFormatUtils.SF.format(new Date()));
		if (CollectionUtils.isNotEmpty(genParamsList)) {
			genParamsList.forEach(genParam -> {
				params.put(genParam.getName(), genParam.getValue());
			});
		}
		this.chainContext.put("params",params);
		this.chainContext.next();
	}

}
