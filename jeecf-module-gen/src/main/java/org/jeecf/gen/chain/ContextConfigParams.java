package org.jeecf.gen.chain;

import java.util.List;
import java.util.Map;

import org.jeecf.gen.model.config.ConfigContext;
import org.jeecf.gen.model.rule.RuleContext;
/**
 * 
 * @author jianyiming
 * @since 2.0
 */
public class ContextConfigParams {

    private String sourcePath;

    private String outZip;

    private String namespaceId;

    private String userId;

    private String tableName;

    private ConfigContext configContext;

    private List<RuleContext> ruleContexts;

    private Map<String, Object> extParams;

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getOutZip() {
        return outZip;
    }

    public void setOutZip(String outZip) {
        this.outZip = outZip;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ConfigContext getConfigContext() {
        return configContext;
    }

    public void setConfigContext(ConfigContext configContext) {
        this.configContext = configContext;
    }

    public List<RuleContext> getRuleContexts() {
        return ruleContexts;
    }

    public void setRuleContexts(List<RuleContext> ruleContexts) {
        this.ruleContexts = ruleContexts;
    }

    public Map<String, Object> getExtParams() {
        return extParams;
    }

    public void setExtParams(Map<String, Object> extParams) {
        this.extParams = extParams;
    }

}
