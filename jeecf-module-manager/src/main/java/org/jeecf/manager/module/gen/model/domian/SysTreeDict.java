package org.jeecf.manager.module.gen.model.domian;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.model.AbstractTreeNamespaceEntity;
import org.jeecf.manager.module.gen.model.result.SysTreeDictResult;
import org.jeecf.manager.validate.groups.Add;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 树组字典实体
 * 
 * @author jianyiming
 *
 */
@ScriptAssert.List({ 
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.leftEqual)", message = "{treeDict.leftEqual.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.rightEqual)", message = "{treeDict.rightEqual.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.event)", message = "{treeDict.event.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.groupName)", message = "{treeDict.groupName.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.result)", message = "{treeDict.result.isEmpty}", groups = { Add.class }),
    @ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notBlank(_this.id,_this.description)", message = "{treeDict.description.isEmpty}", groups = { Add.class }) 
})
@ApiModel(value = "sysTreeDict", description = "树组字典实体")
public class SysTreeDict extends AbstractTreeNamespaceEntity<SysTreeDictResult> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public SysTreeDict() {
    }

    public SysTreeDict(String id) {
        super(id);
    }

    /**
     * 左等式
     */
    @ApiModelProperty(value = "左等式", name = "leftEqual")
    private String leftEqual;
    /**
     * 事件
     */
    @ApiModelProperty(value = "事件", name = "event")
    private Integer event;
    /**
     * 右等式
     */
    @ApiModelProperty(value = "右等式", name = "rightEqual")
    private String rightEqual;
    /**
     * 产出
     */
    @ApiModelProperty(value = "产出", name = "result")
    private String result;
    /**
     * 组别
     */
    @ApiModelProperty(value = "组别", name = "group")
    private String groupName;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述", name = "description")
    private String description;

    public Integer getEvent() {
        return event;
    }

    public void setEvent(Integer event) {
        this.event = event;
    }

    @Length(min = 1, max = 50, message = "{treeDict.result.length}", groups = { Add.class })
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Length(min = 1, max = 50, message = "{treeDict.groupName.length}", groups = { Add.class })
    public String getGroupName() {
        return this.groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Length(min = 1, max = 50, message = "{treeDict.description.length}", groups = { Add.class })
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Length(min = 1, max = 20, message = "{treeDict.leftEqual.length}", groups = { Add.class })
    public String getLeftEqual() {
        return leftEqual;
    }

    public void setLeftEqual(String leftEqual) {
        this.leftEqual = leftEqual;
    }

    @Length(min = 1, max = 20, message = "{treeDict.rightEqual.length}", groups = { Add.class })
    public String getRightEqual() {
        return rightEqual;
    }

    public void setRightEqual(String rightEqual) {
        this.rightEqual = rightEqual;
    }

    @Override
    public void sortList(List<SysTreeDictResult> newList, List<SysTreeDictResult> sourceList, String rootId) {
        for (int i = 0; i < sourceList.size(); i++) {
            SysTreeDictResult sysTreeDict = sourceList.get(i);
            if (("0".equals(rootId) && StringUtils.isEmpty(sysTreeDict.getParentId())) || (StringUtils.isNotEmpty(sysTreeDict.getParentId()) && sysTreeDict.getParentId().equals(rootId))) {
                newList.add(sysTreeDict);
                for (int j = 0; j < sourceList.size(); j++) {
                    SysTreeDict child = sourceList.get(j);
                    if (child.getParentId() != null && String.valueOf(child.getParentId()).equals(sysTreeDict.getId())) {
                        sysTreeDict.setHasChild(true);
                        sortList(newList, sourceList, sysTreeDict.getId());
                        break;
                    }
                }
            }
        }

    }

}
