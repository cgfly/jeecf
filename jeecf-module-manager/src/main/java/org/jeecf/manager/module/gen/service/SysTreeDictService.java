package org.jeecf.manager.module.gen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.service.AbstractTreeNamespaceAuthService;
import org.jeecf.manager.module.gen.dao.SysTreeDictDao;
import org.jeecf.manager.module.gen.model.domian.SysTreeDict;
import org.jeecf.manager.module.gen.model.po.SysTreeDictPO;
import org.jeecf.manager.module.gen.model.query.SysTreeDictQuery;
import org.jeecf.manager.module.gen.model.result.SysTreeDictResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

@Service
public class SysTreeDictService extends AbstractTreeNamespaceAuthService<SysTreeDictDao,SysTreeDictPO,SysTreeDictResult,SysTreeDictQuery,SysTreeDict>{

	  @Override
		@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
		public Response<SysTreeDictResult> save(SysTreeDict sysTreeDict) {
			if (!StringUtils.isEmpty(sysTreeDict.getParentId())) {
				SysTreeDict queryParentsysTreeDict = new SysTreeDict();
				queryParentsysTreeDict.setId(sysTreeDict.getParentId());
				SysTreeDict parentSysTreeDict = this.getByAuth(queryParentsysTreeDict).getData();
				if (StringUtils.isEmpty(parentSysTreeDict.getParentIds())) {
					sysTreeDict.setParentIds(parentSysTreeDict.getId());
				} else {
					sysTreeDict.setParentIds(parentSysTreeDict.getParentIds() + "," + parentSysTreeDict.getId());
				}
				sysTreeDict.setParentId(parentSysTreeDict.getId());
				sysTreeDict.setLevel(parentSysTreeDict.getLevel() + 1);
			} else {
				sysTreeDict.setLevel(1);
				sysTreeDict.setParentIds("");
			}
			if (!sysTreeDict.isNewRecord()) {
				this.updateChilds(sysTreeDict);
			}
			return super.saveByAuth(sysTreeDict);
		}
	    
	    @Override
		@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
		public Response<Integer> delete(SysTreeDict sysTreeDict) {
			return super.deleteByAuth(sysTreeDict);
		}
	    
	    
		@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
		public Response<Integer> deleteWithChilds(SysTreeDict sysTreeDict) {
			super.deleteByAuth(sysTreeDict);
			deleteChilds(sysTreeDict.getId());
			return new Response<Integer>(1);
		}

		@Override
		@Transactional(readOnly = true,rollbackFor=RuntimeException.class)
		public Response<List<SysTreeDictResult>> findChilds(String id) {
			SysTreeDictQuery querySysTreeDict = new SysTreeDictQuery();
			querySysTreeDict.setParentIds(id);
			Response<List<SysTreeDictResult>> sysTreeDictRes = super.findList(new SysTreeDictPO(querySysTreeDict));
			if (sysTreeDictRes.isSuccess() && CollectionUtils.isNotEmpty(sysTreeDictRes.getData())) {
				List<SysTreeDictResult> childMenus = sysTreeDictRes.getData().stream().filter(sysTreeDict -> {
					if (!StringUtils.isEmpty(sysTreeDict.getParentIds())) {
						String[] parentIds = sysTreeDict.getParentIds().split(",");
						for (String parentId : parentIds) {
							if (parentId.equals(id)) {
								return true;
							}
						}
					}
					return false;
				}).collect(Collectors.toList());
				sysTreeDictRes.setData(childMenus);
			}
			return sysTreeDictRes;
		}

		@Override
		@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
		public void updateChilds(SysTreeDict sysTreeDict) {
			List<SysTreeDictResult> childList = findChilds(sysTreeDict.getId()).getData();
			if (CollectionUtils.isNotEmpty(childList)) {
				for (int i = 0; i < childList.size(); i++) {
					SysTreeDict tempSysTreeDict = childList.get(i);
					String parentIds = tempSysTreeDict.getParentIds();
					String[] tempParentIds = parentIds.split(",");
					int level = tempSysTreeDict.getLevel();
					if (tempParentIds != null && tempParentIds.length > 2 && tempParentIds[0].equals(sysTreeDict.getId())) {
						tempParentIds = parentIds.split(sysTreeDict.getId() + ",");
						if (StringUtils.isEmpty(sysTreeDict.getParentIds())) {
							parentIds = sysTreeDict.getId() + "," + tempParentIds[1];
						} else {
							parentIds = sysTreeDict.getParentIds() + "," + sysTreeDict.getId() + "," + tempParentIds[1];
						}
						level = sysTreeDict.getLevel() + tempParentIds[1].split(",").length + 1;
					} else if (tempParentIds != null && tempParentIds.length > 2
							&& tempParentIds[tempParentIds.length - 1].equals(sysTreeDict.getId())) {
						parentIds = sysTreeDict.getParentIds() + "," + sysTreeDict.getId();
						level = sysTreeDict.getLevel() + 1;
					} else if (tempParentIds != null && tempParentIds.length > 2) {
						tempParentIds = parentIds.split("," + sysTreeDict.getId() + ",");
						if (StringUtils.isEmpty(sysTreeDict.getParentIds())) {
							parentIds = sysTreeDict.getId() + "," + tempParentIds[1];
						} else {
							parentIds = sysTreeDict.getParentIds() + "," + sysTreeDict.getId() + "," + tempParentIds[1];
						}
						level = sysTreeDict.getLevel() + tempParentIds[1].split(",").length + 1;
					} else if (tempParentIds != null && tempParentIds.length == 2
							&& tempParentIds[0].equals(sysTreeDict.getId())) {
						if (StringUtils.isEmpty(sysTreeDict.getParentIds())) {
							parentIds = sysTreeDict.getId() + "," + tempSysTreeDict.getParentId();
						} else {
							parentIds = sysTreeDict.getParentIds() + "," + sysTreeDict.getId() + "," + tempSysTreeDict.getParentId();
						}
						level = sysTreeDict.getLevel() + 2;
					} else {
						if (StringUtils.isEmpty(sysTreeDict.getParentIds())) {
							parentIds = sysTreeDict.getId();
						} else {
							parentIds = sysTreeDict.getParentIds() + "," + sysTreeDict.getId();
						}
						level = sysTreeDict.getLevel() + 1;
					}
					tempSysTreeDict.setParentIds(parentIds);
					tempSysTreeDict.setLevel(level);
					super.saveByAuth(tempSysTreeDict);
				}
			}
		}

		@Override
		@Transactional(readOnly = false,rollbackFor=RuntimeException.class)
		public void deleteChilds(String id) {
			List<SysTreeDictResult> childList = findChilds(id).getData();
			if (CollectionUtils.isNotEmpty(childList)) {
				childList.forEach(sysTreeDict -> {
					super.deleteByAuth(sysTreeDict);
				});
			}
		}

		@Override
		@Transactional(readOnly = true,rollbackFor=RuntimeException.class)
		public Response<List<SysTreeDictResult>> getTreeData(SysTreeDictPO sysTreeDictPO, boolean isRoot) {
			List<SysTreeDictResult> newList = new ArrayList<>();
			sysTreeDictPO.setSort(" level,sort ", "asc");
			Response<List<SysTreeDictResult>>  sysTreeDictRes = this.findList(sysTreeDictPO);
			List<SysTreeDictResult> sysTreeDictList = sysTreeDictRes.getData();
			if (isRoot && CollectionUtils.isNotEmpty(sysTreeDictList)) {
				sysTreeDictPO.getData().sortList(newList, sysTreeDictRes.getData(), SysTreeDict.getRootId());
			} else if(!isRoot && CollectionUtils.isNotEmpty(sysTreeDictList)){
				sysTreeDictPO.getData().sortList(newList, sysTreeDictRes.getData(), sysTreeDictList.get(0).getId());
			}
			sysTreeDictRes.setData(newList);
			return sysTreeDictRes;
		}

}
