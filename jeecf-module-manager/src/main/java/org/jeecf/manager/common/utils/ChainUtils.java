package org.jeecf.manager.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.gen.handler.BaseParamHandler;
import org.jeecf.manager.gen.handler.GenHandler;
import org.jeecf.manager.gen.handler.TableParamHandler;
/**
 * 责任链工具类
 * @author jianyiming
 *
 */
public class ChainUtils {
	/**
	 * 获取代码生成责任链
	 * @return
	 */
	public static ChainContext genChainContext() {
		List<AbstractHandler>  chainHandlers = new ArrayList<>();
		chainHandlers.add(new BaseParamHandler());
		chainHandlers.add(new TableParamHandler());
		chainHandlers.add(new GenHandler());
		ChainContext chainContext = new ChainContext(chainHandlers);
		return chainContext;
	} 

}
