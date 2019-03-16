package org.jeecf.manager.common.service;

import java.util.List;
import java.util.Set;

import org.jeecf.common.model.Contain;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.common.model.OfficeAuthEntity;
import org.jeecf.manager.common.utils.OfficeUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 组织结构验证
 * 
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class OfficeAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends OfficeAuthEntity> extends BaseService<D, P, R, Q, T>
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
        return super.get(t);
    }

    @Override
    public Response<List<R>> findListByAuth(P p) {
        String officeId = String.valueOf(UserUtils.getCurrentUser().getSysOfficeId());
        Set<String> officeIds = OfficeUtils.findChilds(officeId);
        officeIds.add(officeId);
        Contain contain = new Contain();
        contain.setColumnName("sysOfficeId");
        contain.setColumnValue(officeIds);
        p.setContain(contain);
        return super.findList(p);
    }

    @Override
    public Response<Integer> countByAuth(P p) {
        String officeId = String.valueOf(UserUtils.getCurrentUser().getSysOfficeId());
        Set<String> officeIds = OfficeUtils.findChilds(officeId);
        officeIds.add(officeId);
        Contain contain = new Contain();
        contain.setColumnName("sysOfficeId");
        contain.setColumnValue(officeIds);
        p.setContain(contain);
        return super.count(p);
    }

    @Override
    public Response<List<R>> findPageByAuth(P p) {
        String officeId = String.valueOf(UserUtils.getCurrentUser().getSysOfficeId());
        Set<String> officeIds = OfficeUtils.findChilds(officeId);
        officeIds.add(officeId);
        Contain contain = new Contain();
        contain.setColumnName("sysOfficeId");
        contain.setColumnValue(officeIds);
        p.setContain(contain);
        return super.findPage(p);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> deleteByAuth(T t) {
        return super.delete(t);
    }
}
