package org.jeecf.manager.module.config.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.config.model.domain.SysUserNamespace;
import org.jeecf.manager.module.config.model.po.SysUserNamespacePO;
import org.jeecf.manager.module.config.model.query.SysUserNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysUserNamespaceResult;

/**
 * 用户命名空间对应
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysUserNamespaceDao extends Dao<SysUserNamespacePO, SysUserNamespaceResult, SysUserNamespaceQuery, SysUserNamespace> {
    /**
     * 根据用户id 更新用户当前命名空间
     * 
     * @param userId
     * @param namespaceId
     * @return
     */
    public Integer updateByUserId(String userId, Integer namespaceId);
}