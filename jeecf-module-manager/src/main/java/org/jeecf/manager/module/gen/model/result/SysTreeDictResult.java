package org.jeecf.manager.module.gen.model.result;

import java.io.Serializable;

import org.jeecf.manager.common.enums.TreeEventEnum;
import org.jeecf.manager.module.gen.model.domian.SysTreeDict;

import io.swagger.annotations.ApiModelProperty;

/**
 * 树组字典 返回实体
 * 
 * @author jianyiming
 *
 */
public class SysTreeDictResult extends SysTreeDict implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 事件名称
	 */
	@ApiModelProperty(value = "事件名称", name = "eventName")
	private String eventName;
	
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public void toCovert() {
		if(this.getEvent() != null) {
			this.setEventName(TreeEventEnum.getName(this.getEvent()));
		}
	}

}
