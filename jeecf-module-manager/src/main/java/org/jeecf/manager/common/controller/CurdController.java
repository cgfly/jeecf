package org.jeecf.manager.common.controller;

import java.util.List;

import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 基础的controller
 * 
 * @author jianyiming
 *
 * @param <Q> 查询实体
 * @param <R> 结果实体
 * @param <S> schema实体
 * @param <T> 实体
 */
public interface CurdController<Q, R, S, T> extends BaseController {
	/**
	 * 查询数据列表
	 * 
	 * @param request
	 * @return
	 */
	public Response<List<R>> list(@RequestBody Request<Q, S> request);

	/**
	 * 保存数据
	 * 
	 * @param t
	 * @return
	 */
	public Response<R> save(T t);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public Response<Integer> delete(String id);

}
