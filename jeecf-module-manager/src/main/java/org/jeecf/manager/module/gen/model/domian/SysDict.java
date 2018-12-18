package org.jeecf.manager.module.gen.model.domian;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统字典
 * 
 * @author GloryJian
 * @version 1.0
 */
@ScriptAssert.List({
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)",message="名称输入不能为空",groups= {Add.class}),
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.type)",message="类型输入不能为空",groups= {Add.class}),
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.label)",message="标签输入不能为空",groups= {Add.class}),
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.description)",message="描述输入不能为空",groups= {Add.class}),
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.value)",message="值输入不能为空",groups= {Add.class})

})
@ApiModel(value="sysDict",description="系统字典实体")
public class SysDict extends NamespaceAuthEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 类型
	 */
	@ApiModelProperty(value="类型",name="type")
	private String type;
	
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称",name="name")
	private String name;
	
	/**
	 * 标签
	 */
	@ApiModelProperty(value="标签",name="label")
	private String label;
	
	/**
	 * 值
	 */
	@ApiModelProperty(value="值",name="value")
	private Integer value;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value="描述",name="description")
	private String description;
	
	public SysDict() {
		super();
	}

	public SysDict(String id) {
		super(id);
	}
	
	@Length(min = 1, max = 50, message = "类型长度必须介于 1 和 50 之间",groups= {Add.class})
	@Pattern(regexp="^[a-zA-Z]+$",message="标签只能由a-zA-Z组成",groups= {Add.class})
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Length(min = 1, max = 20, message = "名称长度必须介于 1 和 20 之间",groups= {Add.class})
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min = 1, max = 20, message = "标签长度必须介于 1 和 20 之间",groups= {Add.class})
	@Pattern(regexp="^[a-zA-Z]+[a-zA-Z_]*[a-zA-Z]$",message="标签只能由a-zA-Z_组成",groups= {Add.class})
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
	@Length(min = 1, max = 50, message = "描述长度必须介于 1 和 50 之间",groups= {Add.class})
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

}