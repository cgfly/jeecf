package org.jeecf.manager.module.userpower.model.domain;

import java.io.Serializable;
import java.util.List;

import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.model.AbstractTreePermissionEntity;
import org.jeecf.manager.module.userpower.model.result.SysPowerResult;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 系统权限
 * 
 * @author GloryJian
 * @version 1.0
 */
@ApiModel(value = "sysPower", description = "系统权限实体")
public class SysPower extends AbstractTreePermissionEntity<SysPowerResult> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 标签
     */
    @ApiModelProperty(value = "标签", name = "label")
    private Integer label;

    public SysPower() {
        super();
    }

    public SysPower(String id) {
        super(id);
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    @Override
    public void sortList(List<SysPowerResult> newList, List<SysPowerResult> sourceList, String rootId) {
        for (int i = 0; i < sourceList.size(); i++) {
            SysPowerResult sysPower = sourceList.get(i);
            if (("0".equals(rootId) && StringUtils.isEmpty(sysPower.getParentId())) || (StringUtils.isNotEmpty(sysPower.getParentId()) && sysPower.getParentId().equals(rootId))) {
                newList.add(sysPower);
                for (int j = 0; j < sourceList.size(); j++) {
                    SysPower child = sourceList.get(j);
                    if (child.getParentId() != null && String.valueOf(child.getParentId()).equals(sysPower.getId())) {
                        sysPower.setHasChild(true);
                        sortList(newList, sourceList, sysPower.getId());
                        break;
                    }
                }
            }
        }
    }
}