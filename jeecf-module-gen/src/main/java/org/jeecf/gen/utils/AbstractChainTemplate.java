package org.jeecf.gen.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.chain.ChainContext;
import org.jeecf.gen.handler.ConfigResolveHandler;
import org.jeecf.gen.handler.GenHandler;
import org.jeecf.gen.handler.RuleResolveHandler;
import org.jeecf.gen.handler.TableParamHandler;

/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public abstract class AbstractChainTemplate {

    private ChainContext chainContext = null;

    public ChainContext initChainContext(TableHook tableHook) {
        List<AbstractHandler> chainHandlers = new ArrayList<>();
        List<AbstractHandler> beforeHandlers = beforeResolve();
        List<AbstractHandler> afterHandlers = afterResolve();
        if (CollectionUtils.isNotEmpty(beforeHandlers)) {
            chainHandlers.addAll(beforeHandlers);
        }
        chainHandlers.add(new ConfigResolveHandler());
        chainHandlers.add(new RuleResolveHandler());
        chainHandlers.add(new TableParamHandler());
        if (CollectionUtils.isNotEmpty(afterHandlers)) {
            chainHandlers.addAll(afterHandlers);
        }
        chainHandlers.add(new GenHandler());
        ChainContext chainContext = new ChainContext(chainHandlers, tableHook);
        return this.chainContext = chainContext;
    }

    public ChainContext getChainContext() {
        return this.chainContext;
    }

    /**
     * 解析之前执行 加入handler拦截器组
     * 
     * @return
     */
    public abstract List<AbstractHandler> beforeResolve();

    /**
     * 解析之后执行 加入handler拦截器组
     * 
     * @return
     */
    public abstract List<AbstractHandler> afterResolve();

}
