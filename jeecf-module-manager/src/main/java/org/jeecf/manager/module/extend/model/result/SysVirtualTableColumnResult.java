package org.jeecf.manager.module.extend.model.result;

import java.io.Serializable;

import org.jeecf.manager.common.enums.EnumUtils;
import org.jeecf.manager.engine.enums.TableTypeEnum;
import org.jeecf.manager.module.extend.model.domain.SysVirtualTableColumn;

/**
 * 虚表结果返回实体
 * 
 * @author jianyiming
 *
 */
public class SysVirtualTableColumnResult extends SysVirtualTableColumn implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String typeName;

	private String isNotNullName;

	private String isKeyName;

	private String isAutoName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getIsNotNullName() {
		return isNotNullName;
	}

	public void setIsNotNullName(String isNotNullName) {
		this.isNotNullName = isNotNullName;
	}

	public String getIsKeyName() {
		return isKeyName;
	}

	public void setIsKeyName(String isKeyName) {
		this.isKeyName = isKeyName;
	}

	public String getIsAutoName() {
		return isAutoName;
	}

	public void setIsAutoName(String isAutoName) {
		this.isAutoName = isAutoName;
	}

	public void toCovert() {
		if (this.getType() != null) {
			this.setTypeName(TableTypeEnum.getName(this.getType()));
		}
		if (this.getIsNotNull() != null) {
			this.setIsNotNullName(EnumUtils.IfType.getName(this.getIsNotNull()));
		}
		if (this.getIsKey() != null) {
			this.setIsKeyName(EnumUtils.IfType.getName(this.getIsKey()));
		}
		if (this.getIsAuto() != null) {
			this.setIsAutoName(EnumUtils.IfType.getName(this.getIsAuto()));
		}
	}

}
