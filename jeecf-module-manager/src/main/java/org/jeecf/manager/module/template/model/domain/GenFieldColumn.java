package org.jeecf.manager.module.template.model.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.jeecf.manager.common.model.BaseEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版参数列表
 * 
 * @author GloryJian
 * @version 1.0
 */
@ApiModel(value = "genFieldColumn", description = "模版参数列表实体")
public class GenFieldColumn extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 关联gen_field
	 */
	@ApiModelProperty(value = "关联gen_field", name = "genFieldId")
	private Integer genFieldId;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", name = "name")
	private String name;
	/**
	 * 允许为空
	 */
	@ApiModelProperty(value = "为空", name = "isNull")
	private Integer isNull;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", name = "descrition")
	private String descrition;

	public GenFieldColumn() {
		super();
	}

	public GenFieldColumn(String id) {
		super(id);
	}

	public Integer getGenFieldId() {
		return genFieldId;
	}

	public void setGenFieldId(Integer genFieldId) {
		this.genFieldId = genFieldId;
	}

	@NotBlank(message="名称输入不能为空",groups= {Add.class})
	@Length(min = 1, max = 20, message = "名称长度必须介于 1 和 20 之间",groups= {Add.class})
	@Pattern(regexp="^[a-zA-Z]+$",message="名称只能由a-zA-Z组成",groups= {Add.class})
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank(message="描述输入不能为空",groups= {Add.class})
	@Length(min = 0, max = 50, message = "描述长度必须介于 0 和 50 之间",groups= {Add.class})
	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
	@NotNull(message="输入不能为空",groups= {Add.class})
	@Min(value=0,message="输入错误",groups= {Add.class})
	@Max(value=1,message="输入错误",groups= {Add.class})
	public Integer getIsNull() {
		return isNull;
	}

	public void setIsNull(Integer isNull) {
		this.isNull = isNull;
	}
	
}