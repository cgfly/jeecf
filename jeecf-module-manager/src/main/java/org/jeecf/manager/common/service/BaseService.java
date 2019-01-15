package org.jeecf.manager.common.service;

import java.util.ArrayList;
import java.util.List;

import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.AbstractEntity;
import org.jeecf.common.model.Page;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.enums.EnumUtils;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.jeecf.manager.common.utils.JqlUtils;
import org.jeecf.manager.module.userpower.dao.SysUserDao;
import org.jeecf.manager.module.userpower.model.result.SysUserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 基础service
 * 
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class BaseService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends AbstractEntity>
		implements Service<P, R, Q, T> {

	@Autowired
	protected D dao;
	
	@Autowired
	protected SysUserDao userDao;

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<R> insert(T t) {
		t.preInsert();
		Integer result = dao.insert(t);
		if (result != null && result > 0) {
			return new Response<>(true, dao.get(t));
		}
		throw new BusinessException(BusinessErrorEnum.INSERT_DATA_FAIL);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<R> update(T t) {
		t.preUpdate();
		Integer result = dao.update(t);
		if (result != null && result > 0) {
			return new Response<>(true, dao.get(t));
		}
		throw new BusinessException(BusinessErrorEnum.UPDATE_DATA_FAIL);
	}

	@Override
	@Transactional(readOnly = false, rollbackFor = RuntimeException.class)
	public Response<R> save(T t) {
		if (t.isNewRecord()) {
			return this.insert(t);
		} else {
			return this.update(t);
		}
	}

	@Override
	public Response<R> get(T t) {
		return new Response<R>(true, dao.get(t));
	}

	@Override
	public Response<List<R>> findList(P p) {
		p.buildSorts();
		p.buildContains();
		if (p.getData().getDelFlag() == null) {
			p.getData().setDelFlag(EnumUtils.DelFlag.NO.getCode());
		}
		Response<List<R>> res = new Response<List<R>>(true, dao.query(p));
		JqlUtils.build(p.getSchema(), res.getData());
		return res;
	}

	@Override
	public Response<Integer> count(P p) {
		p.buildContains();
		if (p.getData().getDelFlag() == null) {
			p.getData().setDelFlag(EnumUtils.DelFlag.NO.getCode());
		}
		return new Response<Integer>(true, dao.count(p));
	}

	@Override
	public Response<List<R>> findPage(P p) {
		Page page = p.getPage();
		p.buildSorts();
		p.buildContains();
		if (p.getData().getDelFlag() == null)  {
			p.getData().setDelFlag(EnumUtils.DelFlag.NO.getCode());
		}
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
	public Response<Integer> delete(T t) {
		return new Response<Integer>(true, dao.delete(t));
	}
	
	/**
	 * 构建创建人
	 * 
	 * @param rList
	 * @return
	 */
	public List<R> buildCreateBy(List<R> rList) {
		List<String> userIds = new ArrayList<>();
		rList.forEach(r -> {
			userIds.add(r.getCreateBy());
		});
		List<SysUserResult> sysUserResultList = userDao.queryByIds(userIds);
		rList.forEach(r -> {
			for (SysUserResult sysUserResult : sysUserResultList) {
				if (r.getCreateBy().equals(sysUserResult.getId())) {
					r.setCreateByName(sysUserResult.getUsername());
					break;
				}
			}
		});
		return rList;
	}

}
