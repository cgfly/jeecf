package org.jeecf.manager.common.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Dao;
import org.jeecf.common.model.Page;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.model.PermissionEntity;
import org.jeecf.manager.common.utils.PermissionUtils;
import org.springframework.transaction.annotation.Transactional;

/**
 * 权限 数据校验service
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public class PermissionAuthService<D extends Dao<P, R, Q, T>, P extends AbstractEntityPO<Q>, R extends T, Q extends T, T extends PermissionEntity>
		extends AbstractAuthService<D, P, R, Q, T> {

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> insertByAuth(T t) {
		return super.insert(t);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> updateByAuth(T t) {
		if (this.getByAuth(t).getData() != null) {
			return super.update(t);
		}
		return new Response<>(null);
	}

	@Override
	public Response<R> getByAuth(T t) {
		Response<R> res = super.get(t);
		if (res.getData() != null) {
			if (PermissionUtils.isExist(res.getData().getPermission())) {
				return res;
			}
		}
		return new Response<>(null);
	}

	@Override
	public Response<List<R>> findListByAuth(P p) {
		Response<List<R>> res = super.findList(p);
		if (CollectionUtils.isNotEmpty(res.getData())) {
			res.setData(PermissionUtils.filter(res.getData()));
		}
		return res;
	}

	@Override
	public Response<Integer> countByAuth(P p) {
		return super.count(p);
	}

	@Override
	public Response<List<R>> findPageByAuth(P p) {
		Response<List<R>> res = this.findListByAuth(p);
		Page page = p.getPage();
		if (page != null) {
			page.setTotal(0);
			page.setStartNo();
			if (CollectionUtils.isNotEmpty(res.getData())) {
				List<R> rList = res.getData();
				page.setTotal(rList.size());
				int startNo = page.getStartNo();
				int total = rList.size();
				int size = page.getSize();
				if (total > startNo) {
					int limit = size;
					if (total < startNo + size) {
						limit = total - startNo;
					}
					res.setData(rList.stream().skip(startNo).limit(limit).collect(Collectors.toList()));

				} else {
					rList = new ArrayList<>();
					res.setData(rList);
				}
			}
			res.setPage(page);
			return res;

		}
		return null;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> deleteByAuth(T t) {
		if (this.getByAuth(t).getData() != null) {
			return super.delete(t);
		}
		return new Response<>(null);
	}

}
