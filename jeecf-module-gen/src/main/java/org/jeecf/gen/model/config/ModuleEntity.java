package org.jeecf.gen.model.config;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jeecf.gen.model.rule.StrategyEntity;
import org.springframework.core.io.Resource;

/**
 * config module 参数体
 * 
 * @author jianyiming
 * @since 1.0
 */
public class ModuleEntity {

    /**
     * 名称
     */
    private String name;
    /**
     * 规则名称
     */
    private String rule = "default";
    /**
     * 分发数据匹配
     */
    private String match;
    /**
     * 模块参数
     */
    private String moduleParams;
    /**
     * 路径集合
     */
    private Set<Resource> paths;
    /**
     * 模块表数据
     */
    private String data;
    /**
     * 模块表
     */
    private Object table;
    /**
     * 模块表数据
     */
    private List<Map<String, Object>> datas;
    /**
     * 模块表
     */
    private List<Object> tables;
    /**
     * 策略体
     */
    private StrategyEntity strategyEntity;

    public String getModuleParams() {
        return moduleParams;
    }

    public void setModuleParams(String moduleParams) {
        this.moduleParams = moduleParams;
    }

    public Set<Resource> getPaths() {
        return paths;
    }

    public void setPaths(Set<Resource> paths) {
        this.paths = paths;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public List<Object> getTables() {
        return tables;
    }

    public void setTables(List<Object> tables) {
        this.tables = tables;
    }

    public Object getTable() {
        return table;
    }

    public void setTable(Object table) {
        this.table = table;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    public StrategyEntity getStrategyEntity() {
        return strategyEntity;
    }

    public void setStrategyEntity(StrategyEntity strategyEntity) {
        this.strategyEntity = strategyEntity;
    }

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

}
