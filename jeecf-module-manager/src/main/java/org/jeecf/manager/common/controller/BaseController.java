package org.jeecf.manager.common.controller;

import java.util.List;

import org.jeecf.common.model.Request;
import org.jeecf.common.model.Response;
import org.springframework.web.bind.annotation.RequestBody;
/**
 * 基础的controller
 * @author jianyiming
 *
 * @param <Q>
 * @param <R>
 * @param <S>
 * @param <T>
 */
public abstract class BaseController<Q,R,S,T> extends AbstractController{
	/**
	 * 查询数据列表
	 * @param request
	 * @return
	 */
	public abstract Response<List<R>> list(@RequestBody Request<Q,S> request);
	/**
	 * 保存数据
	 * @param t
	 * @return
	 */
	public abstract Response<Integer> save(T t);
	/**
	 * 根据主键删除
	 * @param id
	 * @return
	 */
	public abstract Response<Integer> delete(String id);

}
