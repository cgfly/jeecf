package org.jeecf.manager.gen.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.gen.model.BaseTable;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.gen.builder.TableBuilder;
import org.jeecf.manager.gen.enums.RuleStrategyNameEnum;
import org.jeecf.manager.gen.model.config.ConfigContext;
import org.jeecf.manager.gen.model.config.DistributionEntity;
import org.jeecf.manager.gen.model.config.ModuleEntity;
import org.jeecf.manager.gen.model.rule.RuleContext;
import org.jeecf.manager.gen.model.rule.StrategyEntity;
import org.jeecf.manager.gen.strategy.DistributionLikeStrategy;
import org.jeecf.manager.gen.strategy.FilterStrategy;
import org.jeecf.manager.gen.strategy.GroupDataStrategy;
import org.jeecf.manager.gen.strategy.ManyTableStrategy;
import org.jeecf.manager.gen.strategy.TreeDataStrategy;

/**
 * 表参数责任链
 * 
 * @author jianyiming
 *
 */
public class TableParamHandler extends AbstractHandler {

    private ManyTableStrategy manyTableStrategy = new ManyTableStrategy();

    private GroupDataStrategy groupDataStrategy = new GroupDataStrategy();

    private FilterStrategy filterStrategy = new FilterStrategy();

    private TreeDataStrategy treeDataStrategy = new TreeDataStrategy();

    private DistributionLikeStrategy distributionLikeStrategy = new DistributionLikeStrategy();

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
            this.buildFactory(language, tableName, entry.getKey(), entry.getValue(), configContext.getDistributionEntity());
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
    private void buildFactory(Integer language, String tableName, RuleContext ruleContext, List<ModuleEntity> moduleEntitys, DistributionEntity distributionEntity) {
        if (StringUtils.isNotEmpty(tableName)) {
            TableBuilder builder = new TableBuilder();
            BaseTable table = builder.build(tableName);
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
                    buildStrategy(data, table, strategyEntity, moduleEntitys, builder, distributionEntity);
                } else {
                    buildData(moduleEntitys, data, table, distributionEntity);
                }
            } else {
                moduleEntitys.forEach(moduleEntity -> {
                    moduleEntity.setTable(table);
                });
                buildData(moduleEntitys, null, table, distributionEntity);
            }
        }
    }

    /**
     * 构建策略数据
     * 
     * @param data
     * @param strategyEntity
     * @param moduleEntitys
     * @param builder
     */
    private void buildStrategy(String data, Object table, StrategyEntity strategyEntity, List<ModuleEntity> moduleEntitys, TableBuilder builder, DistributionEntity distributionEntity) {
        if (strategyEntity.getName().equals(RuleStrategyNameEnum.MANY.name)) {
            buildTables(moduleEntitys, builder, data, strategyEntity.getField(), distributionEntity);
            return;
        } else if (strategyEntity.getName().equals(RuleStrategyNameEnum.GROUP.name)) {
            buildDatas(moduleEntitys, data, table, strategyEntity.getField(), distributionEntity);
            return;
        } else if (strategyEntity.getName().equals(RuleStrategyNameEnum.TREE.name)) {
            data = treeDataStrategy.handler(data);
            buildData(moduleEntitys, data, table, distributionEntity);
            return;
        }
        throw new BusinessException(BusinessErrorEnum.RULE_STRATEGY_NAME_NOT_MATCH);
    }

    /**
     * 构建数据
     * 
     * @param moduleEntitys
     * @param data
     * @param table
     * @param distributionEntity
     */
    private void buildData(List<ModuleEntity> moduleEntitys, String data, Object table, DistributionEntity distributionEntity) {
        moduleEntitys.forEach(moduleEntity -> {
            if (distributionEntity.isActive()) {
                moduleEntity.setData(buildDistributionStrategy(data, distributionEntity.getField(), moduleEntity.getMatch()));
            } else {
                moduleEntity.setData(data);
            }
            moduleEntity.setTable(table);
        });
    }

    /**
     * 构建分组数据
     * 
     * @param moduleEntitys
     * @param data
     * @param table
     * @param field
     * @param distributionEntity
     */
    private void buildDatas(List<ModuleEntity> moduleEntitys, String data, Object table, String field, DistributionEntity distributionEntity) {
        if (distributionEntity.isActive()) {
            moduleEntitys.forEach(moduleEntity -> {
                String distributionData = buildDistributionStrategy(data, distributionEntity.getField(), moduleEntity.getMatch());
                List<Map<String, Object>> datas = groupDataStrategy.handler(distributionData, field.split(","));
                moduleEntity.setDatas(datas);
                moduleEntity.setTable(table);
            });
        } else {
            List<Map<String, Object>> datas = groupDataStrategy.handler(data, field.split(","));
            moduleEntitys.forEach(moduleEntity -> {
                moduleEntity.setDatas(datas);
                moduleEntity.setTable(table);
            });
        }
    }

    /**
     * 构建多表数据
     * 
     * @param moduleEntitys
     * @param builder
     * @param data
     * @param field
     * @param distributionEntity
     */
    private void buildTables(List<ModuleEntity> moduleEntitys, TableBuilder builder, String data, String field, DistributionEntity distributionEntity) {
        if (distributionEntity.isActive()) {
            moduleEntitys.forEach(moduleEntity -> {
                String distributionData = buildDistributionStrategy(data, distributionEntity.getField(), moduleEntity.getMatch());
                List<Object> tables = manyTableStrategy.handler(distributionData, field, builder);
                moduleEntity.setData(data);
                moduleEntity.setTables(tables);
            });
        } else {
            List<Object> tables = manyTableStrategy.handler(data, field, builder);
            moduleEntitys.forEach(moduleEntity -> {
                moduleEntity.setData(data);
                moduleEntity.setTables(tables);
            });
        }
    }

    /**
     * 构建分发数据
     * 
     * @param data
     * @param field
     * @param match
     * @return
     */
    private String buildDistributionStrategy(String data, String field, String match) {
        return distributionLikeStrategy.handler(data, field, match);
    }

}
