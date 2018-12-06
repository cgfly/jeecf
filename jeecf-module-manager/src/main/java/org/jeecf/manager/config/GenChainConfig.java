package org.jeecf.manager.config;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.gen.handler.BaseParamHandler;
import org.jeecf.manager.gen.handler.GenHandler;
import org.jeecf.manager.gen.handler.TableParamHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 责任链配置
 * @author jianyiming
 *
 */
@Configuration
public class GenChainConfig {
	
	@Bean
	public ChainContext genChainContext() {
		List<AbstractHandler>  chainHandlers = new ArrayList<>();
		chainHandlers.add(new BaseParamHandler());
		chainHandlers.add(new TableParamHandler());
		chainHandlers.add(new GenHandler());
		ChainContext chainContext = new ChainContext(chainHandlers);
		return chainContext;
	} 

}
