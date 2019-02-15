package org.jeecf.gen.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.model.rule.RuleContext;
import org.jeecf.gen.resolve.RuleFilterResolve;
import org.jeecf.gen.resolve.RuleStrategyResolve;
import org.jeecf.gen.utils.GenUtils;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * rule 文件解析
 * 
 * @author jianyiming
 * @since 1.0
 */
public class RuleResolveHandler extends AbstractHandler {

    private static final String RULE_FILE_NAME = "rule.json";

    @Override
    public void process() {
        Map<String, Object> paramsMap = this.chainContext.getParams();
        String sourcePath = contextParams.getSourcePath();
        String rulePath = sourcePath + File.separator + RULE_FILE_NAME;
        File ruleFile = new File(rulePath);
        FileInputStream configIs;
        try {
            configIs = new FileInputStream(ruleFile);
            JsonNode rulesNode = GenUtils.getConfig(configIs);
            List<RuleContext> ruleContexts = new ArrayList<>();
            if (rulesNode != null) {
                if (rulesNode.isArray()) {
                    Iterator<JsonNode> rulesIter = rulesNode.iterator();
                    while (rulesIter.hasNext()) {
                        JsonNode ruleNode = rulesIter.next();
                        ruleContexts.add(this.buildRuleContext(ruleNode, paramsMap));
                    }
                } else {
                    ruleContexts.add(this.buildRuleContext(rulesNode, paramsMap));
                }
            } else {
                ruleContexts.add(new RuleContext());
            }
            contextParams.setRuleContexts(ruleContexts);
            this.chainContext.next();
        } catch (FileNotFoundException e) {
            throw new BusinessException(SysErrorEnum.FILE_NO);
        }
    }

    /**
     * 构建rule上下文
     * 
     * @param ruleNode
     * @return
     */
    private RuleContext buildRuleContext(JsonNode ruleNode, Map<String, Object> paramsMap) {
        RuleContext context = new RuleContext();
        context.setName(resolveName(ruleNode.get("name")));
        context.setData(resolveData(ruleNode.get("data")));
        context.setFilterEntitys(RuleFilterResolve.process(ruleNode.get("filter"), context.isData(), paramsMap, contextParams));
        context.setStrategyEntity(RuleStrategyResolve.process(ruleNode.get("strategy"), context.isData(), paramsMap));
        return context;
    }

    /**
     * 解析data
     * 
     * @param dataNode
     * @return
     */
    private boolean resolveData(JsonNode dataNode) {
        if (dataNode != null) {
            return dataNode.asBoolean();
        }
        return false;
    }

    /**
     * 解析name
     * 
     * @param nameNode
     * @return
     */
    private String resolveName(JsonNode nameNode) {
        if (nameNode != null) {
            return nameNode.asText();
        }
        return "defualt";
    }

}
