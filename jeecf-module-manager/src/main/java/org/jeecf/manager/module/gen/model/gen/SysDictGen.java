package org.jeecf.manager.module.gen.model.gen;

import java.util.List;

import org.jeecf.manager.module.gen.model.domian.SysDict;
/**
 * 数字字典 代码生成实体
 * @author jianyiming
 *
 */
public class SysDictGen {

	/**
	 * 类型
	 */
	private String type;
	
	/**
	 * 描述
	 */
	private String description;
	
	private List<SysDict> sysDictList;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SysDict> getSysDictList() {
		return sysDictList;
	}

	public void setSysDictList(List<SysDict> sysDictList) {
		this.sysDictList = sysDictList;
	}

}
