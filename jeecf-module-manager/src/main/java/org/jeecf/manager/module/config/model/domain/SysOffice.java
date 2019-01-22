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
@ScriptAssert.List({ @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.enname)", message = "英文名称输入不能为空", groups = { Add.class }) })
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

    @Length(min = 1, max = 20, message = "英文名称长度必须介于 1 和 20 之间", groups = { Add.class })
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z_]*[a-zA-Z]$", message = "英文名称只能由a-zA-Z_组成", groups = { Add.class })
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
