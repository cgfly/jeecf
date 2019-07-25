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
    /**
     * 模版路径
     */
    private String sourcePath;
    /**
     * 代码生成路径
     */
    private String outZip;
    /**
     * 命名空间id
     */
    private String namespaceId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 命名空间名称
     */
    private String namespaceName;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 配置文件上下文
     */
    private ConfigContext configContext;
    /**
     * 规则文件上下文
     */
    private List<RuleContext> ruleContexts;
    /**
     * 扩展参数
     */
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

    public String getNamespaceName() {
        return namespaceName;
    }

    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
