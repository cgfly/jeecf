package org.jeecf.manager.module.gen.model.schema;

/**
 * 数据字典 schema
 * 
 * @author jianyiming
 *
 */
public class SysDictSchema {
    /**
     * 主键
     */
    private boolean id = true;

    /**
     * 类型
     */
    private boolean type = true;

    /**
     * 名称
     */
    private boolean name = true;

    /**
     * 标签
     */
    private boolean label = true;

    /**
     * 值
     */
    private boolean value = true;

    /**
     * 描述
     */
    private boolean description = true;
    /**
     * 创建人
     */
    private boolean createBy = true;
    /**
     * 创建时间
     */
    private boolean createDate = true;

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isName() {
        return name;
    }

    public void setName(boolean name) {
        this.name = name;
    }

    public boolean isLabel() {
        return label;
    }

    public void setLabel(boolean label) {
        this.label = label;
    }

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public boolean isDescription() {
        return description;
    }

    public void setDescription(boolean description) {
        this.description = description;
    }

    public boolean isCreateBy() {
        return createBy;
    }

    public void setCreateBy(boolean createBy) {
        this.createBy = createBy;
    }

    public boolean isCreateDate() {
        return createDate;
    }

    public void setCreateDate(boolean createDate) {
        this.createDate = createDate;
    }

    public boolean isId() {
        return id;
    }

    public void setId(boolean id) {
        this.id = id;
    }

}
