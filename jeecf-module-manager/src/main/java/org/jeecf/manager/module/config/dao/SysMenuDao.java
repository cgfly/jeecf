package org.jeecf.manager.module.config.dao;


import org.apache.ibatis.annotations.Mapper;
import org.jeecf.common.model.Dao;
import org.jeecf.manager.module.config.model.domain.SysMenu;
import org.jeecf.manager.module.config.model.po.SysMenuPO;
import org.jeecf.manager.module.config.model.query.SysMenuQuery;
import org.jeecf.manager.module.config.model.result.SysMenuResult;
/**
 * 系统菜单dao
 * @author jianyiming
 *
 */
@Mapper
public interface SysMenuDao extends Dao<SysMenuPO,SysMenuResult,SysMenuQuery,SysMenu>{

	
}