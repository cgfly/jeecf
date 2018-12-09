package org.jeecf.manager.common.service;

import java.util.List;

import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Dao;
import org.jeecf.common.model.Page;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.model.BaseEntity;
import org.jeecf.manager.common.utils.JqlUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.springframework.transaction.annotation.Transactional;
/**
 * 用户权限验证
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class UserAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends BaseEntity>
		extends AbstractAuthService<D, P, R, Q, T> {
	

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> insertByAuth(T t) {
		return super.insert(t);
	}
	
	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> updateByAuth(T t) {
		return super.update(t);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> saveByAuth(T t) {
		return super.saveByAuth(t);
	}

	@Override
	public Response<R> getByAuth(T t) {
		t.setCreateBy(UserUtils.getCurrentUserId());
		return new Response<R>(true, dao.get(t));
	}
	
	@Override
	public Response<List<R>> findListByAuth(P p) {
		p.buildSorts();
		p.buildContains();
		p.getData().setCreateBy(UserUtils.getCurrentUserId());
		Response<List<R>> res = new Response<List<R>>(true, dao.query(p));
		JqlUtils.build(p.getSchema(), res.getData());
		return res;
	}
	
	@Override
	public Response<Integer> countByAuth(P p) {
		p.buildContains();
		p.getData().setCreateBy(UserUtils.getCurrentUserId());
		return new Response<Integer>(true, dao.count(p));
	}

	@Override
	public Response<List<R>> findPageByAuth(P p) {
		Page page = p.getPage();
		p.getData().setCreateBy(UserUtils.getCurrentUserId());
		p.buildSorts();
		p.buildContains();
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
		t.setCreateBy(UserUtils.getCurrentUserId());
		return new Response<Integer>(true, dao.delete(t));
	}

}
