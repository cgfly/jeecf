package org.jeecf.manager.module.config.service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.EncodeUtils;
import org.jeecf.common.utils.IdGenUtils;
import org.jeecf.manager.common.service.AbstractTreeService;
import org.jeecf.manager.module.config.dao.SysMenuDao;
import org.jeecf.manager.module.config.model.domain.SysMenu;
import org.jeecf.manager.module.config.model.po.SysMenuPO;
import org.jeecf.manager.module.config.model.query.SysMenuQuery;
import org.jeecf.manager.module.config.model.result.SysMenuResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
/**
 * 系统菜单 service
 * @author jianyiming
 *
 */
@Service
public class SysMenuService extends AbstractTreeService<SysMenuDao,SysMenuPO,SysMenuResult,SysMenuQuery,SysMenu> {

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> save(SysMenu sysMenu) {
		if (!StringUtils.isEmpty(sysMenu.getParentId())) {
			SysMenu queryParentSysMenu = new SysMenu();
			queryParentSysMenu.setId(sysMenu.getParentId());
			SysMenu parentSysMenu = this.get(queryParentSysMenu).getData();
			if (StringUtils.isEmpty(parentSysMenu.getParentIds())) {
				sysMenu.setParentIds(parentSysMenu.getId());
			} else {
				sysMenu.setParentIds(parentSysMenu.getParentIds() + "," + parentSysMenu.getId());
			}
			sysMenu.setParentId(parentSysMenu.getId());
			sysMenu.setModuleLabel(parentSysMenu.getModuleLabel());
			sysMenu.setLevel(parentSysMenu.getLevel() + 1);
		} else {
			sysMenu.setModuleLabel(sysMenu.getLabel());
			sysMenu.setLevel(1);
		}
		if (!sysMenu.isNewRecord()) {
			this.updateChilds(sysMenu);
			if (sysMenu.getIsIcon() != null) {
				if (sysMenu.getIsIcon() == 1 && StringUtils.isEmpty(sysMenu.getIcon())) {
					sysMenu.setIcon(EncodeUtils.encodeBase64(IdGenUtils.uuid()));
				}
			}
		} else {
			if (sysMenu.getIsIcon() != null) {
				if (sysMenu.getIsIcon() == 1) {
					sysMenu.setIcon(EncodeUtils.encodeBase64(IdGenUtils.uuid()));
				}
			}
		}
		return super.save(sysMenu);
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public Response<Integer> delete(SysMenu sysMenu) {
		super.delete(sysMenu);
		deleteChilds(sysMenu.getId());
		return new Response<Integer>(1);
	}

	@Override
	@Transactional(readOnly = true,rollbackFor=RuntimeException.class)
	public Response<List<SysMenuResult>> findChilds(String id) {
		SysMenuQuery queryMenu = new SysMenuQuery();
		queryMenu.setParentIds(id);
		Response<List<SysMenuResult>> sysMenuRes = super.findList(new SysMenuPO(queryMenu));
		if (sysMenuRes.isSuccess() && CollectionUtils.isNotEmpty(sysMenuRes.getData())) {
			List<SysMenuResult> childMenus = sysMenuRes.getData().stream().filter(sysMenu -> {
				if (!StringUtils.isEmpty(sysMenu.getParentIds())) {
					String[] parentIds = sysMenu.getParentIds().split(",");
					for (String parentId : parentIds) {
						if (parentId.equals(id)) {
							return true;
						}
					}
				}
				return false;
			}).collect(Collectors.toList());
			sysMenuRes.setData(childMenus);
		}
		return sysMenuRes;
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public void updateChilds(SysMenu sysMenu) {
		List<SysMenuResult> childList = findChilds(sysMenu.getId()).getData();
		if (CollectionUtils.isNotEmpty(childList)) {
			for (int i = 0; i < childList.size(); i++) {
				SysMenu tempSysMenu = childList.get(i);
				String parentIds = tempSysMenu.getParentIds();
				String[] tempParentIds = parentIds.split(",");
				int level = tempSysMenu.getLevel();
				if (tempParentIds != null && tempParentIds.length > 2 && tempParentIds[0].equals(sysMenu.getId())) {
					tempParentIds = parentIds.split(sysMenu.getId() + ",");
					if (StringUtils.isEmpty(sysMenu.getParentIds())) {
						parentIds = sysMenu.getId() + "," + tempParentIds[1];
					} else {
						parentIds = sysMenu.getParentIds() + "," + sysMenu.getId() + "," + tempParentIds[1];
					}
					level = sysMenu.getLevel() + tempParentIds[1].split(",").length + 1;
				} else if (tempParentIds != null && tempParentIds.length > 2
						&& tempParentIds[tempParentIds.length - 1].equals(sysMenu.getId())) {
					parentIds = sysMenu.getParentIds() + "," + sysMenu.getId();
					level = sysMenu.getLevel() + 1;
				} else if (tempParentIds != null && tempParentIds.length > 2) {
					tempParentIds = parentIds.split("," + sysMenu.getId() + ",");
					if (StringUtils.isEmpty(sysMenu.getParentIds())) {
						parentIds = sysMenu.getId() + "," + tempParentIds[1];
					} else {
						parentIds = sysMenu.getParentIds() + "," + sysMenu.getId() + "," + tempParentIds[1];
					}
					level = sysMenu.getLevel() + tempParentIds[1].split(",").length + 1;
				} else if (tempParentIds != null && tempParentIds.length == 2
						&& tempParentIds[0].equals(sysMenu.getId())) {
					if (StringUtils.isEmpty(sysMenu.getParentIds())) {
						parentIds = sysMenu.getId() + "," + tempSysMenu.getParentId();
					} else {
						parentIds = sysMenu.getParentIds() + "," + sysMenu.getId() + "," + tempSysMenu.getParentId();
					}
					level = sysMenu.getLevel() + 2;
				} else {
					if (StringUtils.isEmpty(sysMenu.getParentIds())) {
						parentIds = sysMenu.getId();
					} else {
						parentIds = sysMenu.getParentIds() + "," + sysMenu.getId();
					}
					level = sysMenu.getLevel() + 1;
				}
				tempSysMenu.setParentIds(parentIds);
				tempSysMenu.setLevel(level);
				tempSysMenu.setModuleLabel(sysMenu.getModuleLabel());
				String parentPermission = sysMenu.getPermission();
				String moduleLabelPermission = parentPermission.split(":")[0];
				String permission = moduleLabelPermission+":"+tempSysMenu.getPermission().split(":", 2)[1];
				tempSysMenu.setPermission(permission);
				super.save(tempSysMenu);
			}
		}
	}

	@Override
	@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
	public void deleteChilds(String id) {
		List<SysMenuResult> childList = findChilds(id).getData();
		if (CollectionUtils.isNotEmpty(childList)) {
			childList.forEach(sysMenu -> {
				super.delete(sysMenu);
			});
		}
	}

	@Override
	@Transactional(readOnly = true,rollbackFor=RuntimeException.class)
	public Response<List<SysMenuResult>> getTreeData(SysMenuPO sysMenuPO, boolean isRoot) {
		List<SysMenuResult> newList = new ArrayList<>();
		sysMenuPO.setSort(" level,sort ", "asc");
		Response<List<SysMenuResult>> sysMenuRes = this.findList(sysMenuPO);
		List<SysMenuResult> sysMenuList = sysMenuRes.getData();
		if (isRoot && CollectionUtils.isNotEmpty(sysMenuList)) {
			sysMenuPO.getData().sortList(newList, sysMenuRes.getData(), SysMenu.getRootId());
		} else if (!isRoot && CollectionUtils.isNotEmpty(sysMenuList)) {
			sysMenuPO.getData().sortList(newList, sysMenuRes.getData(), sysMenuList.get(0).getId());
		}
		sysMenuRes.setData(newList);
		return sysMenuRes;
	}

}