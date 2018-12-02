package org.jeecf.manager.common.service;

import java.util.List;

import org.jeecf.common.model.AbstractEntity;
import org.jeecf.common.model.AbstractEntityPO;
import org.jeecf.common.model.Dao;
import org.jeecf.common.model.Response;
import org.springframework.transaction.annotation.Transactional;
/**
 * 树图 service
 * @author jianyiming
 *
 * @param <D>
 * @param <P>
 * @param <R>
 * @param <Q>
 * @param <T>
 */
public abstract class AbstractTreeService<D extends Dao<P,R,Q,T>, P extends AbstractEntityPO<Q>,R extends T,Q extends T,T extends AbstractEntity> extends BaseService<D,P,R,Q,T>{
	
	public Response<List<R>> getTreeData(P p){
		return this.getTreeData(p,true);
	}
	/**
	 * 获取tree格式数据
	 * @param p
	 * @param isRoot
	 * @return
	 */
	public abstract Response<List<R>> getTreeData(P p,boolean isRoot);
	
	/**
	 * 查询子数据
	 * @param id
	 * @return
	 */
	public abstract Response<List<R>> findChilds(String id);
	
	/**
	 * 更新子数据
	 * @param t
	 */
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public abstract void updateChilds(T t);
	
	/**
	 * 删除子数据
	 * @param id
	 */
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public abstract void deleteChilds(String id);
	
}
