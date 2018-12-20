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
import org.jeecf.manager.common.utils.JqlUtils;
import org.jeecf.manager.common.utils.NamespaceUtils;
import org.springframework.transaction.annotation.Transactional;
/**
 * 命名空间及数据源验证 service
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class NamespaceAndDbAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends NamespaceAndDbAuthEntity>
extends AbstractAuthService<D, P, R, Q, T> {
	
	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<R> insertByAuth(T t) {
		t.preInsert();
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
		Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
		Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
		if (sysNamespaceId == null || sysDbsourceId == null) {
			return new Response<>(true,null);
		}
		t.setSysNamespaceId(sysNamespaceId);
		t.setSysDbsourceId(sysDbsourceId);
		return new Response<R>(true, dao.get(t));
	}

	@Override
	public Response<List<R>> findListByAuth(P p) {
		p.buildSorts();
		p.buildContains();
		Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
		Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
		if (sysNamespaceId == null || sysDbsourceId == null) {
			return new Response<>(true,new ArrayList<R>());
		}
		p.getData().setSysNamespaceId(sysNamespaceId);
		p.getData().setSysDbsourceId(sysDbsourceId);
		Response<List<R>> res = new Response<List<R>>(true, dao.query(p));
		JqlUtils.build(p.getSchema(), res.getData());
		return res;
	}

	@Override
	public Response<Integer> countByAuth(P p) {
		p.buildContains();
		Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
		Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
		if (sysNamespaceId == null || sysDbsourceId == null) {
			return new Response<Integer>(true,0);
		}
		p.getData().setSysNamespaceId(sysNamespaceId);
		p.getData().setSysDbsourceId(sysDbsourceId);
		return new Response<Integer>(true, dao.count(p));
	}

	@Override
	public Response<List<R>> findPageByAuth(P p) {
		Page page = p.getPage();
		p.buildSorts();
		p.buildContains();
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
		Integer sysNamespaceId = NamespaceUtils.getNamespaceId();
		Integer sysDbsourceId = DbsourceUtils.getSysDbsourceId();
		if (sysNamespaceId == null ) {
			throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
		}
		if (sysDbsourceId == null) {
			throw new BusinessException(BusinessErrorEnum.DARASOURCE_NOT);
		}
		t.setSysNamespaceId(sysNamespaceId);
		t.setSysDbsourceId(sysDbsourceId);
		return new Response<Integer>(true, dao.delete(t));
	}

}