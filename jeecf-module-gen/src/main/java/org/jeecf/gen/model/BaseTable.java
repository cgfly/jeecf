package org.jeecf.gen.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author jianyiming
 *
 */
public class BaseTable extends CommonTable {

    /**
     * 父表外键
     */
    private String parentTableFk;

    /**
     * 父表外键
     */
    private CommonTable parent;
    /**
     * 子表
     */
    private List<CommonTable> childList = new ArrayList<>();

    public String getParentTableFk() {
        return parentTableFk;
    }

    public void setParentTableFk(String parentTableFk) {
        this.parentTableFk = parentTableFk;
    }

    public CommonTable getParent() {
        return parent;
    }

    public void setParent(CommonTable parent) {
        this.parent = parent;
    }

    public List<CommonTable> getChildList() {
        return childList;
    }

    public void setChildList(List<CommonTable> childList) {
        this.childList = childList;
    }

}
