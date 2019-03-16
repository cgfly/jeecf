package org.jeecf.manager.common.service;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Page;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.common.model.NamespaceAndDbAuthEntity;
import org.jeecf.manager.common.utils.DbsourceUtils;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 命名空间及数据源验证 service
 * 
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class NamespaceAndDbAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends NamespaceAndDbAuthEntity> extends BaseService<D, P, R, Q, T>
        implements AuthService<D, P, R, Q, T> {

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<R> insertByAuth(T t) {
        Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
        Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
        if (sysNamespaceId == null) {
            throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
        }
        if (sysDbsourceId == null) {
            throw new BusinessException(BusinessErrorEnum.DARASOURCE_NOT);
        }
        t.setSysNamespaceId(sysNamespaceId);
        t.setSysDbsourceId(sysDbsourceId);
        return super.insert(t);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<R> updateByAuth(T t) {
        Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
        Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
        if (sysNamespaceId == null) {
            throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
        }
        if (sysDbsourceId == null) {
            throw new BusinessException(BusinessErrorEnum.DARASOURCE_NOT);
        }
        t.setSysNamespaceId(sysNamespaceId);
        t.setSysDbsourceId(sysDbsourceId);
        return super.update(t);
    }

    @Override
    public Response<R> getByAuth(T t) {
        Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
        Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
        if (sysNamespaceId == null || sysDbsourceId == null) {
            return new Response<>(true, null);
        }
        t.setSysNamespaceId(sysNamespaceId);
        t.setSysDbsourceId(sysDbsourceId);
        return super.get(t);
    }

    @Override
    public Response<List<R>> findListByAuth(P p) {
        Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
        Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
        if (sysNamespaceId == null || sysDbsourceId == null) {
            return new Response<>(true, new ArrayList<R>());
        }
        p.getData().setSysNamespaceId(sysNamespaceId);
        p.getData().setSysDbsourceId(sysDbsourceId);
        Response<List<R>> res = super.findList(p);
        return res;
    }

    @Override
    public Response<Integer> countByAuth(P p) {
        Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
        Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
        if (sysNamespaceId == null || sysDbsourceId == null) {
            return new Response<Integer>(true, 0);
        }
        p.getData().setSysNamespaceId(sysNamespaceId);
        p.getData().setSysDbsourceId(sysDbsourceId);
        return super.count(p);
    }

    @Override
    public Response<List<R>> findPageByAuth(P p) {
        Page page = p.getPage();
        Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
        Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
        if (sysNamespaceId == null || sysDbsourceId == null) {
            if (page != null) {
                page.setTotal(0);
                page.setStartNo();
            }
            return new Response<>(true, new ArrayList<R>(), page);
        }
        p.getData().setSysNamespaceId(sysNamespaceId);
        p.getData().setSysDbsourceId(sysDbsourceId);
        return super.findPage(p);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> deleteByAuth(T t) {
        Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
        Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
        if (sysNamespaceId == null) {
            throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
        }
        if (sysDbsourceId == null) {
            throw new BusinessException(BusinessErrorEnum.DARASOURCE_NOT);
        }
        t.setSysNamespaceId(sysNamespaceId);
        t.setSysDbsourceId(sysDbsourceId);
        return super.delete(t);
    }

}
