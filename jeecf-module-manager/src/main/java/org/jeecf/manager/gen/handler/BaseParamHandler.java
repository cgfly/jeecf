package org.jeecf.manager.gen.handler;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.utils.DateFormatUtils;
import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.chain.ChainContext;
import org.jeecf.gen.model.GenParams;

/**
 * 基础参数责任链
 * 
 * @author jianyiming
 * @since 1.0
 */
public class BaseParamHandler extends AbstractHandler {

    private Map<String, Object> extMap = null;

    @Override
    public void init(ChainContext context) {
        super.init(context);
        extMap = this.contextParams.getExtParams();
    }

    @Override
    public void process() {
        Map<String, Object> params = chainContext.getParams();
        @SuppressWarnings("unchecked")
        List<GenParams> genParamsList = (List<GenParams>) extMap.get("genParamsList");

        params.put("nowDate", DateFormatUtils.getSfFormat().format(new Date()));
        params.put("namespace", Integer.valueOf(this.contextParams.getNamespaceId()));
        params.put("username", this.contextParams.getUserId());
        if (CollectionUtils.isNotEmpty(genParamsList)) {
            genParamsList.forEach(genParam -> {
                params.put(genParam.getName(), genParam.getValue());
            });
        }
        this.chainContext.next();
    }

}
