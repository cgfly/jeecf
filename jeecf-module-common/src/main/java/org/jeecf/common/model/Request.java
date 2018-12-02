package org.jeecf.common.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 请求实体
 * @author jianyiming
 *
 * @param <T>
 */
@ApiModel(value="request",description="入参实体")
public class Request<T,R> {
	/**
	 * 分页实体
	 */
	@ApiModelProperty(value="分页实体",name="page")
	private Page page;
	/**
	 * 排序实体
	 */
	@ApiModelProperty(value="排序实体",name="sorts")
	private List<Sort> sorts;
    /**
     * 请求数据
     */
	@ApiModelProperty(value="数据实体",name="data")
	private T data;
	
	@ApiModelProperty(value="返回实体定定义",name="data")
	private R schema;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public List<Sort> getSorts() {
		return sorts;
	}

	public void setSorts(List<Sort> sorts) {
		this.sorts = sorts;
	}

	public R getSchema() {
		return schema;
	}

	public void setSchema(R schema) {
		this.schema = schema;
	}
	
}
