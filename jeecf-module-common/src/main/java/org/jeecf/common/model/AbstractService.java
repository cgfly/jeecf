package org.jeecf.common.model;

import java.util.List;
/**
 * 抽象service 
 * @author jianyiming
 *
 * @param <P> PO实体
 * @param <R> 返回实体
 * @param <Q> 查询实体
 * @param <T> domain实体
 */
public abstract class AbstractService<P extends AbstractEntityPO<Q>,R extends T,Q extends T,T extends AbstractEntity > {
	/**
	 * 数据插入
	 * @param t
	 * @return 
	 */
    public abstract Response<Integer> insert(T t);
    /**
     * 数据更新
     * @param t
     * @return
     */
    public abstract Response<Integer> update(T t);
    /**
     * 数据保存
     * @param t
     * @return
     */
    public abstract Response<Integer> save(T t);
    /**
     * 单条数据获取
     * @param t
     * @return
     */
    public abstract Response<R> get(T t);
    /**
     * 查询列表
     * @param p
     * @return
     */
    public abstract Response<List<R>> findList(P p);
    
    /**
     * 分页查询
     * @param p
     * @return
     */
    public abstract Response<List<R>> findPage(P p);
	/**
	 * 统计查询
	 * @param p
	 * @return
	 */
    public abstract Response<Integer> count(P p);
    /**
     * 删除查询
     * @param t
     * @return
     */
    public abstract Response<Integer> delete(T t);
}
