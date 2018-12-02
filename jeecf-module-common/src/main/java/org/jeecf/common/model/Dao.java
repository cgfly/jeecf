package org.jeecf.common.model;

import java.util.List;
/**
 * dao 接口
 * @author jianyiming
 *
 * @param <P> PO实体
 * @param <R> 返回实体
 * @param <Q> 查询实体
 * @param <T> domain实体
 */
public interface Dao<P extends AbstractEntityPO<Q>,R extends T,Q extends T,T extends AbstractEntity> {
	/**
	 * 查询数据
	 * @param p
	 * @return
	 */
	public List<R> query(P p);
	/**
	 * 查询单体数据
	 * @param t
	 * @return
	 */
	public R get(T t);
    /**
     * 插入数据
     * @param t
     * @return
     */
	public Integer insert(T t);
	/**
	 * 更新数据
	 * @param t
	 * @return
	 */
	public Integer update(T t);
    /**
     * 删除数据
     * @param t
     * @return
     */
	public Integer delete(T t);
	/**
	 * 统计数据
	 * @param p
	 * @return
	 */
	public Integer count(P p);
	
}
