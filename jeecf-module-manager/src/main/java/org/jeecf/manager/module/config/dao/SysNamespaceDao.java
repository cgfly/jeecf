package org.jeecf.manager.module.config.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.config.model.po.SysNamespacePO;
import org.jeecf.manager.module.config.model.query.SysNamespaceQuery;
import org.jeecf.manager.module.config.model.result.SysNamespaceResult;

/**
 * 系统命名空间
 * 
 * @author GloryJian
 * @version 1.0
 */
@Mapper
public interface SysNamespaceDao extends Dao<SysNamespacePO, SysNamespaceResult, SysNamespaceQuery, SysNamespace> {

    /**
     * 通过id集合 查询列表
     * 
     * @param ids
     * @return
     */
    public List<SysNamespaceResult> findListByIds(List<Integer> ids);
}