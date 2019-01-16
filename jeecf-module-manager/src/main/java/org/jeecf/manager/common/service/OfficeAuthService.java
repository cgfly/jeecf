package org.jeecf.manager.common.service;

import java.util.List;
import java.util.Set;

import org.jeecf.common.enums.DelFlagEnum;
import org.jeecf.common.model.Contain;
import org.jeecf.common.model.Page;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.common.model.OfficeAuthEntity;
import org.jeecf.manager.common.utils.JqlUtils;
import org.jeecf.manager.common.utils.OfficeUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.springframework.transaction.annotation.Transactional;
/**
 * 组织结构验证
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class OfficeAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends OfficeAuthEntity>
extends BaseService<D, P, R, Q, T> implements AuthService<D, P, R, Q, T>  {
	
	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<R> insertByAuth(T t) {
        return super.insert(t);
	}
	
	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<R> updateByAuth(T t) {
		return super.update(t);
	}

	@Override
	public Response<R> getByAuth(T t) {
		return super.get(t);
	}
	
	@Override
	public Response<List<R>> findListByAuth(P p) {
		Set<String> officeIds = OfficeUtils.findChilds(UserUtils.getCurrentUserId());
		officeIds.add(String.valueOf(UserUtils.getCurrentUser().getSysOfficeId()));
		Contain contain = new Contain();
		contain.setColumnName("sysOfficeId");
		contain.setColumnValue(officeIds);
		p.setContain(contain);
		p.buildSorts();
		p.buildContains();
		p.getData().setDelFlag(DelFlagEnum.NO.getCode());
		Response<List<R>> res = new Response<List<R>>(true, dao.query(p));
		JqlUtils.build(p.getSchema(), res.getData());
		return res;
	}
	
	@Override
	public Response<Integer> countByAuth(P p) {
		Set<String> officeIds = OfficeUtils.findChilds(UserUtils.getCurrentUserId());
		officeIds.add(String.valueOf(UserUtils.getCurrentUser().getSysOfficeId()));
		Contain contain = new Contain();
		contain.setColumnName("sysOfficeId");
		contain.setColumnValue(officeIds);
		p.setContain(contain);
		p.buildContains();
		p.getData().setDelFlag(DelFlagEnum.NO.getCode());
		return new Response<Integer>(true, dao.count(p));
	}

	@Override
	public Response<List<R>> findPageByAuth(P p) {
		Page page = p.getPage();
		Set<String> officeIds = OfficeUtils.findChilds(UserUtils.getCurrentUserId());
		officeIds.add(String.valueOf(UserUtils.getCurrentUser().getSysOfficeId()));
		Contain contain = new Contain();
		contain.setColumnName("sysOfficeId");
		contain.setColumnValue(officeIds);
		p.setContain(contain);
		p.buildSorts();
		p.buildContains();
		p.getData().setDelFlag(DelFlagEnum.NO.getCode());
		if (page != null) {
			page.setTotal(dao.count(p));
			page.setStartNo();
		}
		Response<List<R>> res = new Response<List<R>>(true, dao.query(p), page);
		JqlUtils.build(p.getSchema(), res.getData());
		return res;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
 	public Response<Integer> deleteByAuth(T t) {
		return super.delete(t);
	}
}
