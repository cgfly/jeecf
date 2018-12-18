package org.jeecf.manager.gen.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.utils.GenUtils;
import org.jeecf.manager.gen.model.rule.RuleContext;
import org.jeecf.manager.gen.resolve.RuleFilterResolve;
import org.jeecf.manager.gen.resolve.RuleStrategyResolve;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * rule 文件解析
 * @author jianyiming
 *
 */
public class RuleResolveHandler extends AbstractHandler {
	
	private Map<String, Object> paramsMap = null;
	
	private static RuleFilterResolve ruleFilterResolve = new RuleFilterResolve();
	
	private static RuleStrategyResolve ruleStrategyResolve = new RuleStrategyResolve();

	@Override
	public void init(ChainContext context) {
		this.chainContext = context;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process() {
		this.paramsMap = (Map<String, Object>) this.chainContext.get("params");
		String rulePath = (String) this.chainContext.get("rulePath");
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
						ruleContexts.add(this.buildRuleContext(ruleNode));
					}
				} else {
					ruleContexts.add(this.buildRuleContext(rulesNode));
				}
			} else {
				ruleContexts.add(new RuleContext());
			}
			this.chainContext.put("ruleContexts", ruleContexts);
			this.chainContext.next();
		} catch (FileNotFoundException e) {
			throw new BusinessException(SysErrorEnum.FILE_NO);
		}
	}
	/**
	 * 构建rule上下文
	 * @param ruleNode
	 * @return
	 */
	private RuleContext buildRuleContext(JsonNode ruleNode) {
		RuleContext context = new RuleContext();
		context.setName(resolveName(ruleNode.get("name")));
		context.setData(resolveData(ruleNode.get("data")));
		context.setFilterEntitys(ruleFilterResolve.handler(ruleNode.get("filter"), context.isData(), paramsMap));
		context.setStrategyEntity(ruleStrategyResolve.handler(ruleNode.get("strategy"), context.isData(), paramsMap));
		return context;
	}
	
	/**
	 * 解析data
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
