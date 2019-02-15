package org.jeecf.gen.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.gen.model.BaseTable;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.gen.chain.AbstractHandler;
import org.jeecf.gen.chain.ChainContext;
import org.jeecf.gen.enums.DistributionStrategyEnum;
import org.jeecf.gen.enums.RuleStrategyNameEnum;
import org.jeecf.gen.exception.DataFilterEmptyException;
import org.jeecf.gen.exception.RuleStrategyFieldEmptyException;
import org.jeecf.gen.exception.RuleStrategyNameNotMatchException;
import org.jeecf.gen.model.config.ConfigContext;
import org.jeecf.gen.model.config.DistributionEntity;
import org.jeecf.gen.model.config.ModuleEntity;
import org.jeecf.gen.model.rule.RuleContext;
import org.jeecf.gen.model.rule.StrategyEntity;
import org.jeecf.gen.strategy.DistributionLikeStrategy;
import org.jeecf.gen.strategy.DistributionRegexStrategy;
import org.jeecf.gen.strategy.FilterStrategy;
import org.jeecf.gen.strategy.GroupDataStrategy;
import org.jeecf.gen.strategy.ManyTableStrategy;
import org.jeecf.gen.strategy.TreeDataStrategy;
import org.jeecf.gen.utils.TableHook;

/**
 * 表参数责任链
 * 
 * @author jianyiming
 * @since 1.0
 */
public class TableParamHandler extends AbstractHandler {

    private TableHook tableHook = null;

    @Override
    public void init(ChainContext context) {
        super.init(context);
        this.tableHook = this.chainContext.getTableHook();
    }

    @Override
    public void process() {
        String tableName = contextParams.getTableName();
        ConfigContext configContext = contextParams.getConfigContext();
        List<RuleContext> ruleContexts = contextParams.getRuleContexts();

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
            this.buildFactory(tableName, entry.getKey(), entry.getValue(), configContext.getDistributionEntity());
        }
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
    private void buildFactory(String tableName, RuleContext ruleContext, List<ModuleEntity> moduleEntitys, DistributionEntity distributionEntity) {
        if (StringUtils.isNotEmpty(tableName)) {
            BaseTable table = tableHook.build(tableName);
            if (ruleContext.isData()) {
                String data = FilterStrategy.handler(ruleContext.getFilterEntitys(), tableHook, table);
                StrategyEntity strategyEntity = ruleContext.getStrategyEntity();
                if (strategyEntity != null && StringUtils.isNotBlank(strategyEntity.getName())) {
                    if (StringUtils.isEmpty(data)) {
                        throw new DataFilterEmptyException("data filter is empty ...");
                    }
                    if (StringUtils.isBlank(strategyEntity.getField())) {
                        throw new RuleStrategyFieldEmptyException("rule strategy field is empty ...");
                    }
                    buildStrategy(data, table, strategyEntity, moduleEntitys, distributionEntity);
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
    private void buildStrategy(String data, Object table, StrategyEntity strategyEntity, List<ModuleEntity> moduleEntitys, DistributionEntity distributionEntity) {
        if (strategyEntity.getName().equals(RuleStrategyNameEnum.MANY.name)) {
            buildTables(moduleEntitys, data, strategyEntity.getField(), distributionEntity);
            return;
        } else if (strategyEntity.getName().equals(RuleStrategyNameEnum.GROUP.name)) {
            buildDatas(moduleEntitys, data, table, strategyEntity.getField(), distributionEntity);
            return;
        } else if (strategyEntity.getName().equals(RuleStrategyNameEnum.TREE.name)) {
            data = TreeDataStrategy.handler(data);
            buildData(moduleEntitys, data, table, distributionEntity);
            return;
        }
        throw new RuleStrategyNameNotMatchException("rule strategy name not match ... ");
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
                moduleEntity.setData(buildDistributionStrategy(data, distributionEntity.getField(), moduleEntity.getMatch(), distributionEntity.getStrategy()));
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
                String distributionData = buildDistributionStrategy(data, distributionEntity.getField(), moduleEntity.getMatch(), distributionEntity.getStrategy());
                List<Map<String, Object>> datas = GroupDataStrategy.handler(distributionData, field.split(","));
                moduleEntity.setDatas(datas);
                moduleEntity.setTable(table);
            });
        } else {
            List<Map<String, Object>> datas = GroupDataStrategy.handler(data, field.split(","));
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
    private void buildTables(List<ModuleEntity> moduleEntitys, String data, String field, DistributionEntity distributionEntity) {
        if (distributionEntity.isActive()) {
            moduleEntitys.forEach(moduleEntity -> {
                String distributionData = buildDistributionStrategy(data, distributionEntity.getField(), moduleEntity.getMatch(), distributionEntity.getStrategy());
                List<Object> tables = ManyTableStrategy.handler(distributionData, field, tableHook);
                moduleEntity.setData(data);
                moduleEntity.setTables(tables);
            });
        } else {
            List<Object> tables = ManyTableStrategy.handler(data, field, tableHook);
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
    private String buildDistributionStrategy(String data, String field, String match, String strategy) {
        if (strategy.equals(DistributionStrategyEnum.LIKE.getName())) {
            return DistributionLikeStrategy.handler(data, field, match);
        } else if (strategy.equals(DistributionStrategyEnum.REGEX.getName())) {
            return DistributionRegexStrategy.handler(data, field, match);
        }
        return null;
    }

}
