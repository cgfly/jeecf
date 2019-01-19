package org.jeecf.manager.gen.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.utils.DateFormatUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.gen.model.GenParams;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.userpower.model.domain.SysUser;

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
		SysNamespace sysNamespace = (SysNamespace) this.chainContext.get("sysNamespace");
		SysUser sysUser = (SysUser) this.chainContext.get("sysUser");
		params.put("nowDate", DateFormatUtils.getSfFormat().format(new Date()));
		params.put("namespace",Integer.valueOf(sysNamespace.getId()) );
		params.put("username",sysUser.getUsername());
		if (CollectionUtils.isNotEmpty(genParamsList)) {
			genParamsList.forEach(genParam -> {
				params.put(genParam.getName(), genParam.getValue());
			});
		}
		this.chainContext.put("params",params);
		this.chainContext.next();
	}

}
