package org.jeecf.manager.module.config.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.service.AbstractTreeService;
import org.jeecf.manager.module.config.dao.SysOfficeDao;
import org.jeecf.manager.module.config.model.domain.SysOffice;
import org.jeecf.manager.module.config.model.po.SysOfficePO;
import org.jeecf.manager.module.config.model.query.SysOfficeQuery;
import org.jeecf.manager.module.config.model.result.SysOfficeResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;

/**
 * 组织结构 service
 * 
 * @author jianyiming
 *
 */
@Service
public class SysOfficeService extends AbstractTreeService<SysOfficeDao, SysOfficePO, SysOfficeResult, SysOfficeQuery, SysOffice> {

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<SysOfficeResult> save(SysOffice sysOffice) {
        if (!StringUtils.isEmpty(sysOffice.getParentId())) {
            SysOffice queryParentSysOffice = new SysOffice();
            queryParentSysOffice.setId(sysOffice.getParentId());
            SysOffice parentSysOffice = this.get(queryParentSysOffice).getData();
            if (StringUtils.isEmpty(parentSysOffice.getParentIds())) {
                sysOffice.setParentIds(parentSysOffice.getId());
            } else {
                sysOffice.setParentIds(parentSysOffice.getParentIds() + "," + parentSysOffice.getId());
            }
            sysOffice.setParentId(parentSysOffice.getId());
            sysOffice.setLevel(parentSysOffice.getLevel() + 1);
        } else {
            sysOffice.setLevel(1);
            sysOffice.setParentIds("");
        }
        if (!sysOffice.isNewRecord()) {
            this.updateChilds(sysOffice);
        }
        return super.save(sysOffice);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> delete(SysOffice sysOffice) {
        return super.delete(sysOffice);
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> deleteWithChilds(SysOffice sysOffice) {
        super.delete(sysOffice);
        deleteChilds(sysOffice.getId());
        return new Response<Integer>(1);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<List<SysOfficeResult>> findChilds(String id) {
        SysOfficeQuery querySysOffice = new SysOfficeQuery();
        querySysOffice.setParentIds(id);
        Response<List<SysOfficeResult>> sysOfficeRes = super.findList(new SysOfficePO(querySysOffice));
        if (sysOfficeRes.isSuccess() && CollectionUtils.isNotEmpty(sysOfficeRes.getData())) {
            List<SysOfficeResult> childMenus = sysOfficeRes.getData().stream().filter(sysOffice -> {
                if (!StringUtils.isEmpty(sysOffice.getParentIds())) {
                    String[] parentIds = sysOffice.getParentIds().split(",");
                    for (String parentId : parentIds) {
                        if (parentId.equals(id)) {
                            return true;
                        }
                    }
                }
                return false;
            }).collect(Collectors.toList());
            sysOfficeRes.setData(childMenus);
        }
        return sysOfficeRes;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public void updateChilds(SysOffice sysOffice) {
        List<SysOfficeResult> childList = findChilds(sysOffice.getId()).getData();
        if (CollectionUtils.isNotEmpty(childList)) {
            for (int i = 0; i < childList.size(); i++) {
                SysOffice tempSysOffice = childList.get(i);
                String parentIds = tempSysOffice.getParentIds();
                String[] tempParentIds = parentIds.split(",");
                int level = tempSysOffice.getLevel();
                if (tempParentIds != null && tempParentIds.length > 2 && tempParentIds[0].equals(sysOffice.getId())) {
                    tempParentIds = parentIds.split(sysOffice.getId() + ",");
                    if (StringUtils.isEmpty(sysOffice.getParentIds())) {
                        parentIds = sysOffice.getId() + "," + tempParentIds[1];
                    } else {
                        parentIds = sysOffice.getParentIds() + "," + sysOffice.getId() + "," + tempParentIds[1];
                    }
                    level = sysOffice.getLevel() + tempParentIds[1].split(",").length + 1;
                } else if (tempParentIds != null && tempParentIds.length > 2 && tempParentIds[tempParentIds.length - 1].equals(sysOffice.getId())) {
                    parentIds = sysOffice.getParentIds() + "," + sysOffice.getId();
                    level = sysOffice.getLevel() + 1;
                } else if (tempParentIds != null && tempParentIds.length > 2) {
                    tempParentIds = parentIds.split("," + sysOffice.getId() + ",");
                    if (StringUtils.isEmpty(sysOffice.getParentIds())) {
                        parentIds = sysOffice.getId() + "," + tempParentIds[1];
                    } else {
                        parentIds = sysOffice.getParentIds() + "," + sysOffice.getId() + "," + tempParentIds[1];
                    }
                    level = sysOffice.getLevel() + tempParentIds[1].split(",").length + 1;
                } else if (tempParentIds != null && tempParentIds.length == 2 && tempParentIds[0].equals(sysOffice.getId())) {
                    if (StringUtils.isEmpty(sysOffice.getParentIds())) {
                        parentIds = sysOffice.getId() + "," + tempSysOffice.getParentId();
                    } else {
                        parentIds = sysOffice.getParentIds() + "," + sysOffice.getId() + "," + tempSysOffice.getParentId();
                    }
                    level = sysOffice.getLevel() + 2;
                } else {
                    if (StringUtils.isEmpty(sysOffice.getParentIds())) {
                        parentIds = sysOffice.getId();
                    } else {
                        parentIds = sysOffice.getParentIds() + "," + sysOffice.getId();
                    }
                    level = sysOffice.getLevel() + 1;
                }
                tempSysOffice.setParentIds(parentIds);
                tempSysOffice.setLevel(level);
                super.save(tempSysOffice);
            }
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public void deleteChilds(String id) {
        List<SysOfficeResult> childList = findChilds(id).getData();
        if (CollectionUtils.isNotEmpty(childList)) {
            childList.forEach(sysOffice -> {
                super.delete(sysOffice);
            });
        }
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Response<List<SysOfficeResult>> getTreeData(SysOfficePO sysOfficePO, boolean isRoot) {
        List<SysOfficeResult> newList = new ArrayList<>();
        sysOfficePO.setSort(" level,sort ", "asc");
        Response<List<SysOfficeResult>> sysOfficeRes = this.findList(sysOfficePO);
        List<SysOfficeResult> sysOfficeList = sysOfficeRes.getData();
        if (isRoot && CollectionUtils.isNotEmpty(sysOfficeList)) {
            sysOfficePO.getData().sortList(newList, sysOfficeRes.getData(), SysOffice.getRootId());
        } else if (!isRoot && CollectionUtils.isNotEmpty(sysOfficeList)) {
            sysOfficePO.getData().sortList(newList, sysOfficeRes.getData(), sysOfficeList.get(0).getId());
        }
        sysOfficeRes.setData(newList);
        return sysOfficeRes;
    }

}
