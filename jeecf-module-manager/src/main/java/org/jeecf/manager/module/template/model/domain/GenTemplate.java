package org.jeecf.manager.module.template.model.domain;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模版配置
 * 
 * @author GloryJian
 * @version 1.0
 */
@ApiModel(value = "genTemplate", description = "生成模版实体")
@ScriptAssert.List({
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.name)",message="名称输入不能为空",groups= {Add.class}),
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.language)",message="语言输入不能为空",groups= {Add.class}),
	@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.fileBasePath)",message="模版文件基础路径输入不能为空",groups= {Add.class})
})
public class GenTemplate extends NamespaceAuthEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 名称
	 */
	@ApiModelProperty(value = "名称", name = "name")
	private String name;
	/**
	 * 语言
	 */
	@ApiModelProperty(value = "语言", name = "language")
	private Integer language;
	/**
	 * 关联gen_field
	 */
	@ApiModelProperty(value = "关联gen_field", name = "genFieldId")
	private Integer genFieldId;
	/**
	 * 模版文件基础路径
	 */
	@ApiModelProperty(value = "模版文件基础路径", name = "fileBasePath")
	private String fileBasePath;
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述", name = "descrition")
	private String descrition;
   
	public GenTemplate() {
		super();
	}

	public GenTemplate(String id) {
		super(id);
	}

	@Length(min = 1, max = 20, message = "名称长度必须介于 1 和 20 之间",groups= {Add.class})
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	@Min(value = 1,message="语言输入错误",groups= {Add.class})
	@Max(value = 2,message="语言输入错误",groups= {Add.class})
	public Integer getLanguage() {
		return language;
	}

	public void setLanguage(Integer language) {
		this.language = language;
	}
	
	public Integer getGenFieldId() {
		return genFieldId;
	}
	
	public void setGenFieldId(Integer genFieldId) {
		this.genFieldId = genFieldId;
	}

	@Length(min = 1, max = 100, message = "模版文件基础路径长度必须介于 1 和 100 之间",groups= {Add.class})
	public String getFileBasePath() {
		return fileBasePath;
	}

	public void setFileBasePath(String fileBasePath) {
		this.fileBasePath = fileBasePath;
	}
	
	@Length(min = 1, max = 50, message = "描述长度必须介于 1 和 50 之间",groups= {Add.class})
	public String getDescrition() {
		return descrition;
	}

	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}
}