package org.jeecf.manager.module.config.model.domain;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.PermissionEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统命名空间
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)",message="名称输入不能为空",groups= {Add.class}),
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.description)",message="描述输入不能为空",groups= {Add.class}),
})
@ApiModel(value = "sysNamespace", description = "命名空间配置实体")
public class SysNamespace extends PermissionEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", name = "name")
	private String name;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", name = "description")
	private String description;

	public SysNamespace() {
		super();
	}

	public SysNamespace(String id) {
		super(id);
	}
	
	@Length(min = 1, max = 20, message = "名称长度必须介于 1 和 20 之间",groups= {Add.class})
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 1, max = 50, message = "描述长度必须介于 1 和 50 之间",groups= {Add.class})
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}