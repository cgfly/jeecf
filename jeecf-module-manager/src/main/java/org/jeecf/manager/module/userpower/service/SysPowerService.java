package org.jeecf.manager.module.userpower.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.service.AbstractTreeService;
import org.jeecf.manager.module.userpower.dao.SysPowerDao;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.po.SysPowerPO;
import org.jeecf.manager.module.userpower.model.query.SysPowerQuery;
import org.jeecf.manager.module.userpower.model.result.SysPowerResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

/**
 * 系统权限
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysPowerService extends AbstractTreeService<SysPowerDao,SysPowerPO,SysPowerResult,SysPowerQuery,SysPower> {
	
    @Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<SysPowerResult> save(SysPower sysPower) {
		if (!StringUtils.isEmpty(sysPower.getParentId())) {
			SysPower queryParentSysPower = new SysPower();
			queryParentSysPower.setId(sysPower.getParentId());
			SysPower parentSysPower = this.get(queryParentSysPower).getData();
			if (StringUtils.isEmpty(parentSysPower.getParentIds())) {
				sysPower.setParentIds(parentSysPower.getId());
			} else {
				sysPower.setParentIds(parentSysPower.getParentIds() + "," + parentSysPower.getId());
			}
			sysPower.setParentId(parentSysPower.getId());
			sysPower.setLevel(parentSysPower.getLevel() + 1);
		} else {
			sysPower.setLevel(1);
			sysPower.setParentIds("");
		}
		if (!sysPower.isNewRecord()) {
			this.updateChilds(sysPower);
		}
		return super.save(sysPower);
	}
    
    @Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> delete(SysPower sysPower) {
		return super.delete(sysPower);
	}
    
    
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> deleteWithChilds(SysPower sysPower) {
		super.delete(sysPower);
		deleteChilds(sysPower.getId());
		return new Response<Integer>(1);
	}

	@Override
	@Transactional(readOnly = true,rollbackFor=RuntimeException.class)
	public Response<List<SysPowerResult>> findChilds(String id) {
		SysPowerQuery querySysPower = new SysPowerQuery();
		querySysPower.setParentIds(id);
		Response<List<SysPowerResult>> sysPowerRes = super.findList(new SysPowerPO(querySysPower));
		if (sysPowerRes.isSuccess() && CollectionUtils.isNotEmpty(sysPowerRes.getData())) {
			List<SysPowerResult> childMenus = sysPowerRes.getData().stream().filter(sysPower -> {
				if (!StringUtils.isEmpty(sysPower.getParentIds())) {
					String[] parentIds = sysPower.getParentIds().split(",");
					for (String parentId : parentIds) {
						if (parentId.equals(id)) {
							return true;
						}
					}
				}
				return false;
			}).collect(Collectors.toList());
			sysPowerRes.setData(childMenus);
		}
		return sysPowerRes;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public void updateChilds(SysPower sysPower) {
		List<SysPowerResult> childList = findChilds(sysPower.getId()).getData();
		if (CollectionUtils.isNotEmpty(childList)) {
			for (int i = 0; i < childList.size(); i++) {
				SysPower tempSysPower = childList.get(i);
				String parentIds = tempSysPower.getParentIds();
				String[] tempParentIds = parentIds.split(",");
				int level = tempSysPower.getLevel();
				if (tempParentIds != null && tempParentIds.length > 2 && tempParentIds[0].equals(sysPower.getId())) {
					tempParentIds = parentIds.split(sysPower.getId() + ",");
					if (StringUtils.isEmpty(sysPower.getParentIds())) {
						parentIds = sysPower.getId() + "," + tempParentIds[1];
					} else {
						parentIds = sysPower.getParentIds() + "," + sysPower.getId() + "," + tempParentIds[1];
					}
					level = sysPower.getLevel() + tempParentIds[1].split(",").length + 1;
				} else if (tempParentIds != null && tempParentIds.length > 2
						&& tempParentIds[tempParentIds.length - 1].equals(sysPower.getId())) {
					parentIds = sysPower.getParentIds() + "," + sysPower.getId();
					level = sysPower.getLevel() + 1;
				} else if (tempParentIds != null && tempParentIds.length > 2) {
					tempParentIds = parentIds.split("," + sysPower.getId() + ",");
					if (StringUtils.isEmpty(sysPower.getParentIds())) {
						parentIds = sysPower.getId() + "," + tempParentIds[1];
					} else {
						parentIds = sysPower.getParentIds() + "," + sysPower.getId() + "," + tempParentIds[1];
					}
					level = sysPower.getLevel() + tempParentIds[1].split(",").length + 1;
				} else if (tempParentIds != null && tempParentIds.length == 2
						&& tempParentIds[0].equals(sysPower.getId())) {
					if (StringUtils.isEmpty(sysPower.getParentIds())) {
						parentIds = sysPower.getId() + "," + tempSysPower.getParentId();
					} else {
						parentIds = sysPower.getParentIds() + "," + sysPower.getId() + "," + tempSysPower.getParentId();
					}
					level = sysPower.getLevel() + 2;
				} else {
					if (StringUtils.isEmpty(sysPower.getParentIds())) {
						parentIds = sysPower.getId();
					} else {
						parentIds = sysPower.getParentIds() + "," + sysPower.getId();
					}
					level = sysPower.getLevel() + 1;
				}
				tempSysPower.setParentIds(parentIds);
				tempSysPower.setLevel(level);
				super.save(tempSysPower);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public void deleteChilds(String id) {
		List<SysPowerResult> childList = findChilds(id).getData();
		if (CollectionUtils.isNotEmpty(childList)) {
			childList.forEach(sysPower -> {
				super.delete(sysPower);
			});
		}
	}

	@Override
	@Transactional(readOnly = true,rollbackFor=RuntimeException.class)
	public Response<List<SysPowerResult>> getTreeData(SysPowerPO sysPowerPO, boolean isRoot) {
		List<SysPowerResult> newList = new ArrayList<>();
		sysPowerPO.setSort(" level,sort ", "asc");
		Response<List<SysPowerResult>>  sysPowerRes = this.findList(sysPowerPO);
		List<SysPowerResult> sysPowerList = sysPowerRes.getData();
		if (isRoot && CollectionUtils.isNotEmpty(sysPowerList)) {
			sysPowerPO.getData().sortList(newList, sysPowerRes.getData(), SysPower.getRootId());
		} else if(!isRoot && CollectionUtils.isNotEmpty(sysPowerList)){
			sysPowerPO.getData().sortList(newList, sysPowerRes.getData(), sysPowerList.get(0).getId());
		}
		sysPowerRes.setData(newList);
		return sysPowerRes;
	}
	
}