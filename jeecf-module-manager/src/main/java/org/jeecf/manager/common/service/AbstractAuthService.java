package org.jeecf.manager.common.service;

import java.util.List;

import org.jeecf.common.model.AbstractEntity;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.dao.Dao;
import org.jeecf.manager.common.model.AbstractEntityPO;
import org.springframework.transaction.annotation.Transactional;
/**
 * 抽象 数据校验service
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public abstract class AbstractAuthService<D extends Dao<P,R,Q,T>, P extends AbstractEntityPO<Q>,R extends T,Q extends T,T extends AbstractEntity> extends BaseService<D,P,R,Q,T> {
	
	
	/**
	 * 插入数据权限验证
	 * @param t
	 * @return
	 */
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public abstract Response<R> insertByAuth(T t);
	/**
	 * 更新数据权限验证
	 * @param t
	 * @return
	 */
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public abstract Response<R> updateByAuth(T t);
	
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<R> saveByAuth(T t) {
		if (t.isNewRecord()) {
			return this.insertByAuth(t);
		} else {
			return this.updateByAuth(t);
		}
	}

	/**
	 * 单条获取数据权限验证
	 * @param t
	 * @return
	 */
	public abstract Response<R> getByAuth(T t);
    /**
     * 查询数据权限验证
     * @param p
     * @return
     */
	public abstract Response<List<R>> findListByAuth(P p);

	/**
	 * 统计数据权限验证
	 * @param p
	 * @return
	 */
	public abstract Response<Integer> countByAuth(P p);
	/**
	 * 分页查询权限验证
	 * @param p
	 * @return
	 */
	public abstract Response<List<R>> findPageByAuth(P p);
    
	/**
	 * 删除数据权限验证
	 * @param t
	 * @return
	 */
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public abstract Response<Integer> deleteByAuth(T t);
}
