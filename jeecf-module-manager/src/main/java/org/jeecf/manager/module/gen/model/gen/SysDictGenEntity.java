package org.jeecf.manager.module.gen.model.gen;

import java.util.List;

import org.jeecf.manager.common.model.GenEntity;
/**
 * 系统数据字典 schema 实体
 * @author jianyiming
 *
 */
public class SysDictGenEntity extends GenEntity {
	
	private List<SysDictGen> sysDictGenList;

	public List<SysDictGen> getSysDictGenList() {
		return sysDictGenList;
	}

	public void setSysDictGOList(List<SysDictGen> sysDictGenList) {
		this.sysDictGenList = sysDictGenList;
	}
	
	
}
