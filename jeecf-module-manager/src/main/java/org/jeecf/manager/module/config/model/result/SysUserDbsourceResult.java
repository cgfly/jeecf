package org.jeecf.manager.module.config.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.config.model.domain.SysUserDbsource;

/**
 * 系统用户数据源返回实体
 * 
 * @author jianyiming
 * @version 2.0
 */
public class SysUserDbsourceResult extends SysUserDbsource implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 命名空间名称
     */
    private String dbsourceName;
    /**
     * 命名空间逻辑删除
     */
    private Integer dbsourceDelFlag;

    public String getDbsourceName() {
        return dbsourceName;
    }

    public void setDbsourceName(String dbsourceName) {
        this.dbsourceName = dbsourceName;
    }

    public Integer getDbsourceDelFlag() {
        return dbsourceDelFlag;
    }

    public void setDbsourceDelFlag(Integer dbsourceDelFlag) {
        this.dbsourceDelFlag = dbsourceDelFlag;
    }

}
