package org.jeecf.manager.module.extend.model.schema;
/**
 * OSGI 插件 schema
 * @author jianyiming
 *
 */
public class SysOsgiPluginSchema {
	
	private boolean id = true;
	
	private boolean name = true;
	
	private boolean fileName = true;
	
	private boolean boundleType = true;
	
	private boolean boundleTypeName = true;
	
	private boolean sysNamespaceId = true;
	
	private boolean description = true;
	
	private boolean delFlag = true;
	
	private boolean createDate = true;
	
	private boolean updateDate = true;
	
	public boolean isId() {
		return id;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public boolean isName() {
		return name;
	}

	public void setName(boolean name) {
		this.name = name;
	}

	public boolean isFileName() {
		return fileName;
	}

	public void setFileName(boolean fileName) {
		this.fileName = fileName;
	}

	public boolean isBoundleType() {
		return boundleType;
	}

	public void setBoundleType(boolean boundleType) {
		this.boundleType = boundleType;
	}
	
	public boolean isBoundleTypeName() {
		return boundleTypeName;
	}

	public void setBoundleTypeName(boolean boundleTypeName) {
		this.boundleTypeName = boundleTypeName;
	}

	public boolean isSysNamespaceId() {
		return sysNamespaceId;
	}

	public void setSysNamespaceId(boolean sysNamespaceId) {
		this.sysNamespaceId = sysNamespaceId;
	}

	public boolean isDescription() {
		return description;
	}

	public void setDescription(boolean description) {
		this.description = description;
	}

	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
	}

	public boolean isCreateDate() {
		return createDate;
	}

	public void setCreateDate(boolean createDate) {
		this.createDate = createDate;
	}

	public boolean isUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(boolean updateDate) {
		this.updateDate = updateDate;
	}

}
