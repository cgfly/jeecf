package org.jeecf.manager.gen.utils;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.utils.AbstractChainTemplate;
import org.jeecf.manager.gen.handler.BaseParamHandler;
import org.jeecf.manager.gen.handler.PluginHandler;
import org.jeecf.manager.gen.handler.ToolParamHandler;
/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class ChainTemplateImpl extends AbstractChainTemplate {

    @Override
    public List<AbstractHandler> beforeResolve() {
        List<AbstractHandler> beforeHandlers = new ArrayList<AbstractHandler>();
        beforeHandlers.add(new BaseParamHandler());
        beforeHandlers.add(new ToolParamHandler());
        beforeHandlers.add(new PluginHandler());
        return beforeHandlers;
    }

    @Override
    public List<AbstractHandler> afterResolve() {
        return null;
    }

}
