package org.jeecf.manager.module.userpower.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.userpower.model.domain.SysUser;
import org.jeecf.manager.module.userpower.model.po.SysUserPO;
import org.jeecf.manager.module.userpower.model.query.SysUserQuery;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;

/**
 * 系统用户
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysUserDao extends Dao<SysUserPO, SysUserResult, SysUserQuery, SysUser> {
    /**
     * 根据用户集合 查询所有用户
     * 
     * @param ids
     * @return
     */
    public List<SysUserResult> queryByIds(List<String> ids);

}