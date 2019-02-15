package org.jeecf.gen.model;

import java.util.List;

/**
 * gen模版实体
 * 
 * @author jianyiming
 * @since 1.0
 */
public class GenTemplateEntity {

    /**
     * 模版id
     */
    private Integer templateId;
    /**
     * 表名
     */
    private String tableName;
    /**
     * 参数
     */
    private List<GenParams> params;

    public List<GenParams> getParams() {
        return params;
    }

    public void setParams(List<GenParams> params) {
        this.params = params;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

}
