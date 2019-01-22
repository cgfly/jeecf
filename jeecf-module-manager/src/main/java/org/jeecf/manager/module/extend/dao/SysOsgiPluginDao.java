package org.jeecf.manager.module.extend.dao;

import org.apache.ibatis.annotations.Mapper;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.module.extend.model.domain.SysOsgiPlugin;
import org.jeecf.manager.module.extend.model.po.SysOsgiPluginPO;
import org.jeecf.manager.module.extend.model.query.SysOsgiPluginQuery;
import org.jeecf.manager.module.extend.model.result.SysOsgiPluginResult;

/**
 * OSGI 插件DAO
 * 
 * @author jianyiming
 *
 */
@Mapper
public interface SysOsgiPluginDao extends Dao<SysOsgiPluginPO, SysOsgiPluginResult, SysOsgiPluginQuery, SysOsgiPlugin> {
    /**
     * 通过插件名称更新数据
     * 
     * @param sysOsgiPlugin
     * @return
     */
    public Integer updateByName(SysOsgiPlugin sysOsgiPlugin);
}
