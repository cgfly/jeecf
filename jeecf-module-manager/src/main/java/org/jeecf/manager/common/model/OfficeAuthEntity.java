package org.jeecf.manager.common.model;

import java.util.Set;

import org.hibernate.validator.constraints.ScriptAssert;
import org.jeecf.manager.validate.groups.Add;

/**
 * 组织结构安全验证实体
 * 
 * @author jianyiming
 *
 */
@ScriptAssert(lang = "javascript", script = "org.jeecf.manager.validate.constraints.Script.notNull(_this.id,_this.sysOfficeId)", message = "{office.isEmpty}", groups = { Add.class })
public class OfficeAuthEntity extends BaseEntity {

    public OfficeAuthEntity() {
    }

    public OfficeAuthEntity(String id) {
        super(id);
    }

    /**
     * 组织结构id
     */
    private Integer sysOfficeId;
    /**
     * 组织结构id集
     */
    private Set<String> sysOfficeIds;

    public Integer getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(Integer sysOfficeId) {
        this.sysOfficeId = sysOfficeId;
    }

    public Set<String> getSysOfficeIds() {
        return sysOfficeIds;
    }

    public void setSysOfficeIds(Set<String> sysOfficeIds) {
        this.sysOfficeIds = sysOfficeIds;
    }

}
