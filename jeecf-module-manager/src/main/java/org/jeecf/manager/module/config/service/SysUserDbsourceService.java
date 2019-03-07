package org.jeecf.manager.module.config.service;

import org.jeecf.common.model.Response;
import org.jeecf.manager.common.service.BaseService;
import org.jeecf.manager.module.config.dao.SysUserDbsourceDao;
import org.jeecf.manager.module.config.model.domain.SysUserDbsource;
import org.jeecf.manager.module.config.model.po.SysUserDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysUserDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysUserDbsourceResult;
import org.springframework.stereotype.Service;

/**
 * 用户数据源对应 service
 * 
 * @author jianyiming
 * @version 2.0
 */
@Service
public class SysUserDbsourceService extends BaseService<SysUserDbsourceDao, SysUserDbsourcePO, SysUserDbsourceResult, SysUserDbsourceQuery, SysUserDbsource> {

    public Response<Integer> updateByUserId(String userId, Integer dbsourceId) {
        return new Response<>(this.dao.updateByUserId(userId, dbsourceId));
    }

}
