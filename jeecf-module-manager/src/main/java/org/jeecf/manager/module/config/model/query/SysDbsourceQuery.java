package org.jeecf.manager.module.config.model.query;

import java.io.Serializable;
import java.util.Date;

import org.jeecf.manager.module.config.model.domain.SysDbsource;
/**
 * 系统db连接源 实体
 * @author jianyiming
 *
 */
public class SysDbsourceQuery extends SysDbsource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date beginCreateDate;
	private Date endCreateDate;
	private Date beginUpdateDate;
	private Date endUpdateDate;

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

}
