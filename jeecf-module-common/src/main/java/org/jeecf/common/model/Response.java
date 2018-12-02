package org.jeecf.common.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应实体
 * @author jianyiming
 *
 * @param <T>
 */
@ApiModel(value="response",description="回参实体")
public class Response<T> implements Serializable {

	private static final long serialVersionUID = -7783475488658605915L;

	public Response() {
		this(false);
	}

	public Response(boolean success) {
		this(success, null);
	}

	public Response(boolean success, T data) {
		this(success, data,null);
	}
	
	public Response(boolean success, T data,Page page) {
		this.setSuccess(success);
		this.data = data;
		this.page = page;
	}

	public Response(T data) {
		this(true, data);
	}

	/**
	 * 是否成功 <br/>
	 * true:成功 false:失败
	 */
	@ApiModelProperty(value="成功标识",name="success")
	private boolean success = false;
	/**
	 * 返回数据
	 */
	@ApiModelProperty(value="数据体",name="data")
	private T data;
	/**
	 * 返回分页信息
	 */
	@ApiModelProperty(value="分页信息",name="page")
	private Page page;
	/**
	 * 错误码
	 */
	@ApiModelProperty(value="错误码",name="errorCode")
	private int errorCode;
	
	/**
	 * 错误描述
	 */
	@ApiModelProperty(value="错误信息",name="errorMessage")
	private String errorMessage;
	

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public <V> void copyValue(Response<V> response) {
		this.setPage(response.getPage());
		this.setErrorCode(response.getErrorCode());
		this.setErrorMessage(response.getErrorMessage());
		this.setSuccess(response.isSuccess());
	}
	
	
}