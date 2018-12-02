package org.jeecf.manager.module.userpower.model.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jeecf.manager.common.model.BaseEntity;
import org.jeecf.manager.validate.constraints.English;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统用户密码修改
 * 
 * @author GloryJian
 * @version 1.0
 */
@ApiModel(value="sysPwd",description="系统用户密码实体")
public class SysPwd extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 密码
	 */
	@ApiModelProperty(value="密码",name="password")
	private String password;
	
	/**
	 * 新密码
	 */
	@ApiModelProperty(value="新密码",name="password")
	private String newPassword;

	@NotBlank(message="密码输入不能为空",groups= {Add.class})
	@Length(min = 1, max = 64, message = "密码长度必须介于 1 和 64 之间",groups={Add.class})
	@English(message="密码只能为英文字符",groups= {Add.class})
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
	@NotBlank(message="新密码输入不能为空",groups= {Add.class})
	@Length(min = 1, max = 64, message = "新密码长度必须介于 1 和 64 之间",groups={Add.class})
	@English(message="新密码只能为英文字符",groups= {Add.class})
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}
