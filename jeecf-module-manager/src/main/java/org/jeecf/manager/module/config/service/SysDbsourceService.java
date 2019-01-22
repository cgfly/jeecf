package org.jeecf.manager.module.config.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.common.utils.DesEncryptUtils;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.enums.UsableEnum;
import org.jeecf.manager.common.service.PermissionAuthService;
import org.jeecf.manager.common.utils.JdbcUtils;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.config.DynamicDataSourceContextHolder;
import org.jeecf.manager.config.MultipleDataSource;
import org.jeecf.manager.module.config.dao.SysDbsourceDao;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.jeecf.manager.module.config.model.po.SysDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysDbsourceResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 系统数据源
 * 
 * @author GloryJian
 * @version 1.0
 */
@Service
public class SysDbsourceService extends PermissionAuthService<SysDbsourceDao, SysDbsourcePO, SysDbsourceResult, SysDbsourceQuery, SysDbsource> {

    @Value("${spring.datasource.primary.url}")
    private String url;

    @Value("${spring.datasource.primary.username}")
    private String username;

    @Value("${spring.datasource.primary.password}")
    private String password;

    @Value("${encrypt.key}")
    private String encryptKey;

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<SysDbsourceResult> saveByAuth(SysDbsource sysDbsource) {
        Response<SysDbsourceResult> res = super.saveByAuth(sysDbsource);
        if (res.isSuccess() && res.getData() != null) {
            SysDbsourceResult sysDb = res.getData();
            try {
                String password = DesEncryptUtils.decryptWithBase64(sysDb.getPassword(), encryptKey);
                boolean flag = JdbcUtils.test(sysDb.getUrl(), sysDb.getUserName(), password);
                if (flag) {
                    sysDb.setUsable(UsableEnum.YES.getCode());
                    this.initDbSource();
                } else {
                    sysDb.setUsable(UsableEnum.NO.getCode());
                }
                super.saveByAuth(sysDb);
            } catch (Exception e) {
                throw new BusinessException(BusinessErrorEnum.DECRYPT_FAIL);
            }
        }
        return res;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Response<Integer> deleteByAuth(SysDbsource sysDbsource) {
        Response<Integer> response = super.deleteByAuth(sysDbsource);
        if (response.getData() != null && response.getData() != 0) {
            this.initDbSource();
        }
        return response;
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public void initDbSource() {
        List<SysDbsourceResult> dbsourceList = super.findList(new SysDbsourcePO(new SysDbsourceQuery())).getData();
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>(10);
        DataSource defualtDataSource = DynamicDataSourceContextHolder.getDefaultDataSource();
        targetDataSources.put(DynamicDataSourceContextHolder.getDafualtKey(), defualtDataSource);
        SysDbsource sysDb = new SysDbsource();
        sysDb.setUrl(url);
        sysDb.setUserName(username);
        sysDb.setKeyName(DynamicDataSourceContextHolder.getDafualtKey());
        try {
            sysDb.setPassword(DesEncryptUtils.encryptWithBase64(password, encryptKey));
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(BusinessErrorEnum.ENCRYPT_FAIL);
        }
        boolean isDefualtExit = false;
        if (CollectionUtils.isNotEmpty(dbsourceList)) {
            for (SysDbsource sysDbSource : dbsourceList) {
                if (sysDbSource.getKeyName().equals(sysDb.getKeyName())) {
                    isDefualtExit = true;
                    sysDbSource.setUrl(sysDb.getUrl());
                    sysDbSource.setKeyName(sysDb.getKeyName());
                    sysDbSource.setUserName(sysDb.getUserName());
                    sysDbSource.setPassword(sysDb.getPassword());
                    super.dao.update(sysDbSource);
                } else {
                    if (sysDbSource.getUsable() == UsableEnum.YES.getCode()) {
                        try {
                            sysDbSource.setPassword(DesEncryptUtils.decryptWithBase64(sysDb.getPassword(), encryptKey));
                        } catch (Exception e) {
                            throw new BusinessException(BusinessErrorEnum.ENCRYPT_FAIL);
                        }
                        DataSource dbSource = DynamicDataSourceContextHolder.dataBinder(sysDbSource);
                        targetDataSources.put(sysDbSource.getKeyName(), dbSource);
                    }
                }
            }
        }
        if (!isDefualtExit) {
            sysDb.setCreateDate(new Date());
            sysDb.setUpdateDate(new Date());
            super.dao.insert(sysDb);
        }
        MultipleDataSource muliSource = SpringContextUtils.getBean(MultipleDataSource.class);
        muliSource.addTargetDataSources(targetDataSources);
    }
}