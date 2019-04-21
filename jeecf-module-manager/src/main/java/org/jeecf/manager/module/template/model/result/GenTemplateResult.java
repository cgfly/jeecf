package org.jeecf.manager.module.template.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.template.model.domain.GenTemplate;
import org.jeecf.osgi.enums.LanguageEnum;

import io.swagger.annotations.ApiModelProperty;

/**
 * 代码生成模版返回实体
 * 
 * @author jianyiming
 *
 */
public class GenTemplateResult extends GenTemplate implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 代码属性名称
     */
    @ApiModelProperty(value = "代码属性名称", name = "genFieldName")
    private String genFieldName;
    /**
     * 语言名称
     */
    @ApiModelProperty(value = "语言名称", name = "languageName")
    private String languageName;
    /**
     * 是否在其他命名空间中存在
     */
    @ApiModelProperty(value = "是否存在", name = "isExit")
    private Integer isExit;

    public String getGenFieldName() {
        return genFieldName;
    }

    public void setGenFieldName(String genFieldName) {
        this.genFieldName = genFieldName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
    
    public Integer getIsExit() {
        return isExit;
    }

    public void setIsExit(Integer isExit) {
        this.isExit = isExit;
    }

    public void toConvert() {
        if (this.getLanguage() != null) {
            this.setLanguageName(LanguageEnum.getName(this.getLanguage()));
        }
    }

}
