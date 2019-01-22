package org.jeecf.manager.common.service;

import java.util.List;

import org.jeecf.common.model.Response;

/**
 * 抽象service
 * 
 * @author jianyiming
 *
 * @param <P> PO实体
 * @param <R> 返回实体
 * @param <Q> 查询实体
 * @param <T> domain实体
 */
public interface Service<P, R, Q, T> {
    /**
     * 数据插入
     * 
     * @param t
     * @return
     */
    public Response<R> insert(T t);

    /**
     * 数据更新
     * 
     * @param t
     * @return
     */
    public Response<R> update(T t);

    /**
     * 数据保存
     * 
     * @param t
     * @return
     */
    public Response<R> save(T t);

    /**
     * 单条数据获取
     * 
     * @param t
     * @return
     */
    public Response<R> get(T t);

    /**
     * 查询列表
     * 
     * @param p
     * @return
     */
    public Response<List<R>> findList(P p);

    /**
     * 分页查询
     * 
     * @param p
     * @return
     */
    public Response<List<R>> findPage(P p);

    /**
     * 统计查询
     * 
     * @param p
     * @return
     */
    public Response<Integer> count(P p);

    /**
     * 删除查询
     * 
     * @param t
     * @return
     */
    public Response<Integer> delete(T t);
}
