package org.jeecf.manager.common.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModelProperty;
/**
 * 权限实体
 * @author jianyiming
 *
 */
public class PermissionEntity extends BaseEntity{
	
	/**
	 * 权限
	 */
	@ApiModelProperty(value="权限",name="permission")
	private String permission;
	
	public PermissionEntity() {
		super();
	}

	public PermissionEntity(String id) {
		super(id);
	}
	
	@NotNull(message="权限输入不能为空",groups= {Add.class})
	@Length(min = 1, max = 50, message = "权限长度必须介于 1 和 50 之间",groups={Add.class})
	@Pattern(regexp="^[a-zA-Z]+[a-zA-Z:]*[a-zA-Z]$",message="权限只能由a-zA-Z:组成",groups= {Add.class})
	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
}
