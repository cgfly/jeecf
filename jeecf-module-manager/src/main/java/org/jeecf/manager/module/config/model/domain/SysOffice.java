package org.jeecf.manager.module.config.model.domain;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.model.AbstractTreeEntity;
import org.jeecf.manager.module.config.model.result.SysOfficeResult;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 组织机构实体
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.enname)", message = "{office.enname.isEmpty}", groups = { Add.class }) })
@ApiModel(value = "sysOffice", description = "组织结构实体")
public class SysOffice extends AbstractTreeEntity<SysOfficeResult> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SysOffice() {
        super();
    }

    public SysOffice(String id) {
        super(id);
    }

    /**
     * 英文名称
     */
    @ApiModelProperty(value = "英文名称", name = "enname")
    private String enname;

    @Length(min = 1, max = 20, message = "{office.enname.length}", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z0-9_.-]+$", message = "{office.enname.pattern}", groups = { Add.class })
    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    @Override
    public void sortList(List<SysOfficeResult> newList, List<SysOfficeResult> sourceList, String rootId) {
        for (int i = 0; i < sourceList.size(); i++) {
            SysOfficeResult sysOffice = sourceList.get(i);
            if (("0".equals(rootId) && StringUtils.isEmpty(sysOffice.getParentId())) || (StringUtils.isNotEmpty(sysOffice.getParentId()) && sysOffice.getParentId().equals(rootId))) {
                newList.add(sysOffice);
                for (int j = 0; j < sourceList.size(); j++) {
                    SysOfficeResult child = sourceList.get(j);
                    if (child.getParentId() != null && String.valueOf(child.getParentId()).equals(sysOffice.getId())) {
                        sysOffice.setHasChild(true);
                        sortList(newList, sourceList, sysOffice.getId());
                        break;
                    }
                }
            }
        }
    }

}
