package org.jeecf.manager.common.service;

import java.util.List;

import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Page;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.common.model.NamespaceAuthEntity;
import org.jeecf.manager.common.utils.JqlUtils;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 命名空间 数据校验service
 * 
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class NamespaceAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends NamespaceAuthEntity>
		extends AbstractAuthService<D, P, R, Q, T> {

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<R> insertByAuth(T t) {
		t.preInsert();
		t.setSysNamespaceId(NamespaceUtils.getNamespaceId());
		Integer result = dao.insert(t);
		if (result != null && result > 0) {
			return new Response<>(true, dao.get(t));
		}
		throw new BusinessException(BusinessErrorEnum.INSERT_DATA_FAIL);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<R> updateByAuth(T t) {
		t.preUpdate();
		t.setSysNamespaceId(NamespaceUtils.getNamespaceId());
		Integer result = dao.update(t);
		if (result != null && result > 0) {
			return new Response<>(true, dao.get(t));
		} 
		throw new BusinessException(BusinessErrorEnum.UPDATE_DATA_FAIL);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<R> saveByAuth(T t) {
		return super.saveByAuth(t);
	}

	@Override
	public Response<R> getByAuth(T t) {
		t.setSysNamespaceId(NamespaceUtils.getNamespaceId());
		return new Response<R>(true, dao.get(t));
	}

	@Override
	public Response<List<R>> findListByAuth(P p) {
		p.buildSorts();
		p.buildContains();
		p.getData().setSysNamespaceId(NamespaceUtils.getNamespaceId());
		Response<List<R>> res = new Response<List<R>>(true, dao.query(p));
		JqlUtils.build(p.getSchema(), res.getData());
		return res;
	}

	@Override
	public Response<Integer> countByAuth(P p) {
		p.buildContains();
		p.getData().setSysNamespaceId(NamespaceUtils.getNamespaceId());
		return new Response<Integer>(true, dao.count(p));
	}

	@Override
	public Response<List<R>> findPageByAuth(P p) {
		Page page = p.getPage();
		p.buildSorts();
		p.buildContains();
		p.getData().setSysNamespaceId(NamespaceUtils.getNamespaceId());
		if (page != null) {
			page.setTotal(dao.count(p));
			page.setStartNo();
		}
		Response<List<R>> res = new Response<List<R>>(true, dao.query(p), page);
		JqlUtils.build(p.getSchema(), res.getData());
		return res;
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<Integer> deleteByAuth(T t) {
		t.setSysNamespaceId(NamespaceUtils.getNamespaceId());
		return new Response<Integer>(true, dao.delete(t));
	}

}
