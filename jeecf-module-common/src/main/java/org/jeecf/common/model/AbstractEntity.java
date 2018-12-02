package org.jeecf.common.model;

import java.util.Date;

import org.springframework.util.StringUtils;

import io.swagger.annotations.ApiModelProperty;
/**
 * 抽象实体
 * @author jianyiming
 *
 */
public abstract class AbstractEntity {
	
	/**
	 * 主键
	 */
	@ApiModelProperty(value="id",name="id")
	private String id;
	
	/**
	 * 创建人
	 */
	@ApiModelProperty(value="创建人Id",name="createBy")
	private String createBy;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",name="createDate")
	private Date createDate;
	
	/**
	 * 更新人
	 */
	@ApiModelProperty(value="更新人Id",name="updateBy")
	private String updateBy;
	
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",name="updateDate")
	private Date updateDate;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注",name="remark")
	private String remark;
	
	/**
	 * 逻辑删除
	 */
	@ApiModelProperty(value="逻辑删除",name="delFlag")
	private Integer delFlag;
	
	/**
	 * 是否为新记录
	 */
	private boolean isNewRecord = false;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public String getRemark() {
		return remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isNewRecord() {
		return this.isNewRecord || StringUtils.isEmpty(this.getId());
	}
	public void setNewRecord(boolean isNewRecord) {
		this.isNewRecord = isNewRecord;
	}
	
	/**
	 * 插入前执行
	 */
	public abstract void preInsert();
	
	/**
	 * 更新前执行
	 */
	public abstract void preUpdate();
	
}
