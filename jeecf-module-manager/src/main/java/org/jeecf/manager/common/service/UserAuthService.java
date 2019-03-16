package org.jeecf.manager.common.service;

import java.util.List;

import org.jeecf.common.model.Response;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.common.model.BaseEntity;
import org.jeecf.manager.common.utils.UserUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户权限验证
 * 
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class UserAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends BaseEntity> extends BaseService<D, P, R, Q, T>
        implements AuthService<D, P, R, Q, T> {

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<R> insertByAuth(T t) {
        return super.insert(t);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<R> updateByAuth(T t) {
        return super.update(t);
    }

    @Override
    public Response<R> getByAuth(T t) {
        t.setCreateBy(UserUtils.getCurrentUserId());
        return super.get(t);
    }

    @Override
    public Response<List<R>> findListByAuth(P p) {
        p.getData().setCreateBy(UserUtils.getCurrentUserId());
        return super.findList(p);
    }

    @Override
    public Response<Integer> countByAuth(P p) {
        p.getData().setCreateBy(UserUtils.getCurrentUserId());
        return super.count(p);
    }

    @Override
    public Response<List<R>> findPageByAuth(P p) {
        p.getData().setCreateBy(UserUtils.getCurrentUserId());
        return super.findPage(p);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> deleteByAuth(T t) {
        t.setCreateBy(UserUtils.getCurrentUserId());
        return super.delete(t);
    }

}
