package org.jeecf.manager.module.config.model.query;

import java.io.Serializable;
import java.util.Date;

import org.jeecf.manager.module.config.model.domain.SysUserDbsource;

/**
 * 系统用户数据源查询实体
 * 
 * @author jianyiming
 * @version 2.0
 */
public class SysUserDbsourceQuery extends SysUserDbsource implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 命名空间逻辑删除
     */
    private Integer dbsourceDelFlag = 0;
    /**
     * 数据源名称
     */
    private String dbsourceName;
    /**
     * 创建时间开始时间
     */
    private Date beginCreateDate;
    /**
     * 创建时间结束时间
     */
    private Date endCreateDate;
    /**
     * 更新时间开始时间
     */
    private Date beginUpdateDate;
    /**
     * 更新时间结束时间
     */
    private Date endUpdateDate;

    public Integer getDbsourceDelFlag() {
        return dbsourceDelFlag;
    }

    public void setDbsourceDelFlag(Integer dbsourceDelFlag) {
        this.dbsourceDelFlag = dbsourceDelFlag;
    }

    public Date getBeginCreateDate() {
        return beginCreateDate;
    }

    public void setBeginCreateDate(Date beginCreateDate) {
        this.beginCreateDate = beginCreateDate;
    }

    public Date getEndCreateDate() {
        return endCreateDate;
    }

    public void setEndCreateDate(Date endCreateDate) {
        this.endCreateDate = endCreateDate;
    }

    public Date getBeginUpdateDate() {
        return beginUpdateDate;
    }

    public void setBeginUpdateDate(Date beginUpdateDate) {
        this.beginUpdateDate = beginUpdateDate;
    }

    public Date getEndUpdateDate() {
        return endUpdateDate;
    }

    public void setEndUpdateDate(Date endUpdateDate) {
        this.endUpdateDate = endUpdateDate;
    }

    public String getDbsourceName() {
        return dbsourceName;
    }

    public void setDbsourceName(String dbsourceName) {
        this.dbsourceName = dbsourceName;
    }
    
}
