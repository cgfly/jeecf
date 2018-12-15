package org.jeecf.manager.gen.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.gen.builder.AbstractLanguageBuilder;
import org.jeecf.manager.gen.builder.LanguageBuilderFactory;
import org.jeecf.manager.gen.enums.RuleStrategyNameEnum;
import org.jeecf.manager.gen.model.config.ConfigContext;
import org.jeecf.manager.gen.model.config.ModuleEntity;
import org.jeecf.manager.gen.model.rule.RuleContext;
import org.jeecf.manager.gen.model.rule.StrategyEntity;
import org.jeecf.manager.gen.strategy.FilterStrategy;
import org.jeecf.manager.gen.strategy.GroupDataStrategy;
import org.jeecf.manager.gen.strategy.ManyTableStrategy;

/**
 * 表参数责任链
 * 
 * @author jianyiming
 *
 */
public class TableParamHandler extends AbstractHandler {

	private static ManyTableStrategy manyTableStrategy = new ManyTableStrategy();

	private static GroupDataStrategy groupDataStrategy = new GroupDataStrategy();

	private static FilterStrategy filterStrategy = new FilterStrategy();

	@Override
	public void init(ChainContext context) {
		this.chainContext = context;
	}

	@Override
	public void process() {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) this.chainContext.get("params");
		String tableName = (String) this.chainContext.get("tableName");
		Integer language = (Integer) this.chainContext.get("language");
		ConfigContext configContext = (ConfigContext) this.chainContext.get("configContext");
		@SuppressWarnings("unchecked")
		List<RuleContext> ruleContexts = (List<RuleContext>) this.chainContext.get("ruleContexts");
		List<ModuleEntity> moduleEntitys = configContext.getModuleEntitys();
		Map<RuleContext, List<ModuleEntity>> filterMap = new HashMap<>(10);
		moduleEntitys.forEach(module -> {
			for (RuleContext rule : ruleContexts) {
				if (module.getRule().equals(rule.getName())) {
					List<ModuleEntity> moduleEntityList = filterMap.get(rule);
					if (moduleEntityList == null) {
						moduleEntityList = new ArrayList<>();
						filterMap.put(rule, moduleEntityList);
					}
					module.setStrategyEntity(rule.getStrategyEntity());
					moduleEntityList.add(module);
					break;
				}
			}
		});
		for (Map.Entry<RuleContext, List<ModuleEntity>> entry : filterMap.entrySet()) {
			this.buildFactory(language, tableName, entry.getKey(), entry.getValue());
		}
		this.chainContext.put("params", params);
		this.chainContext.next();
	}

	/**
	 * 构建工厂
	 * 
	 * @param language
	 * @param tableName
	 * @param ruleContext
	 * @param moduleEntitys
	 */
	private void buildFactory(Integer language, String tableName, RuleContext ruleContext,
			List<ModuleEntity> moduleEntitys) {
		if (StringUtils.isNotEmpty(tableName)) {
			AbstractLanguageBuilder builder = LanguageBuilderFactory.getBuiler(language);
			Object table = builder.build(tableName);
			if (ruleContext.isData()) {
				String data = filterStrategy.handler(ruleContext.getFilterEntitys(), builder);
				StrategyEntity strategyEntity = ruleContext.getStrategyEntity();
				if (strategyEntity != null && StringUtils.isNotBlank(strategyEntity.getName())) {
					if (StringUtils.isEmpty(data)) {
						throw new BusinessException(BusinessErrorEnum.DATA_NOT_EXIT);
					}
					if (StringUtils.isBlank(strategyEntity.getField())) {
						throw new BusinessException(BusinessErrorEnum.RULE_STRATEGY_FIELD_NOT_EMPTY);
					}
					buildStrategy(data, table, strategyEntity, moduleEntitys, builder);
				} else {
					buildData(moduleEntitys, data, table, null, null);
				}
			} else {
				moduleEntitys.forEach(moduleEntity -> {
					moduleEntity.setTable(table);
				});
				buildData(moduleEntitys, null, table, null, null);
			}
		}
	}

	/**
	 * 构建策略
	 * 
	 * @param data
	 * @param strategyEntity
	 * @param moduleEntitys
	 * @param builder
	 */
	private void buildStrategy(String data, Object table, StrategyEntity strategyEntity,
			List<ModuleEntity> moduleEntitys, AbstractLanguageBuilder builder) {
		if (strategyEntity.getName().equals(RuleStrategyNameEnum.MANY.name)) {
			List<Object> tables = manyTableStrategy.handler(data, strategyEntity.getField(), builder);
			if (CollectionUtils.isNotEmpty(tables)) {
				buildData(moduleEntitys, data, null, null, tables);
				return;
			}
			throw new BusinessException(BusinessErrorEnum.RULE_TABLE_PARAM_MANY_NOT_QUERY);
		} else if (strategyEntity.getName().equals(RuleStrategyNameEnum.GROUP.name)) {
			List<Map<String, Object>> datas = groupDataStrategy.handler(data, strategyEntity.getField().split(","));
			if (CollectionUtils.isNotEmpty(datas)) {
				buildData(moduleEntitys, null, table, datas, null);
				return;
			}
			throw new BusinessException(BusinessErrorEnum.RULE_DATA_GROUP_ERROR);
		}
		throw new BusinessException(BusinessErrorEnum.RULE_STRATEGY_NAME_NOT_MATCH);
	}

	/**
	 * 构建数据
	 * 
	 * @param moduleEntitys
	 * @param data
	 * @param table
	 * @param datas
	 * @param tables
	 */
	public void buildData(List<ModuleEntity> moduleEntitys, String data, Object table, List<Map<String, Object>> datas,
			List<Object> tables) {
		moduleEntitys.forEach(moduleEntity -> {
			moduleEntity.setData(data);
			moduleEntity.setDatas(datas);
			moduleEntity.setTable(table);
			moduleEntity.setTables(tables);
		});
	}

}
