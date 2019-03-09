package org.jeecf.manager.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.enums.UsableEnum;
import org.jeecf.manager.common.utils.DbsourceUtils;
import org.jeecf.manager.common.utils.JdbcUtils;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.jeecf.manager.common.utils.SpringContextUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.jeecf.manager.module.config.model.domain.SysUserDbsource;
import org.jeecf.manager.module.config.model.po.SysUserDbsourcePO;
import org.jeecf.manager.module.config.model.query.SysUserDbsourceQuery;
import org.jeecf.manager.module.config.model.result.SysUserDbsourceResult;
import org.jeecf.manager.module.config.service.SysUserDbsourceService;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
//import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.jdbc.DataSourceBuilder;

/**
 * 动态数据源持有类
 * 
 * @author jianyiming
 *
 */
public class DynamicDataSourceContextHolder {

    /**
     * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
     * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<String>();

    private static DataSource DEFAULT_DATASOURCE;

    private static final String DEFAULT_DATASOURCE_KEY = "defaultDataSourceKey";

    // private static ConversionService CONVERSION_SERVICE = new
    // DefaultConversionService();

    private static Binder BINDER;

    public static final String DATA_SOURCE_SUFFIX = "dataSourceKey";

    /**
     * 使用setDataSourceType设置当前的
     * 
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        CONTEXT_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }

    public static DataSource getDefaultDataSource() {
        return DEFAULT_DATASOURCE;
    }

    public static void setDefaultDataSource(DataSource defaultDataSource) {
        DynamicDataSourceContextHolder.DEFAULT_DATASOURCE = defaultDataSource;
    }

    public static String getDafualtKey() {
        return DynamicDataSourceContextHolder.DEFAULT_DATASOURCE_KEY;
    }
//
//    public static ConversionService getConversionService() {
//        return CONVERSION_SERVICE;
//    }

    public static void setBinder(Binder binder) {
        if (BINDER == null) {
            BINDER = binder;
        }
    }

    @SuppressWarnings("unchecked")
    public static DataSource dataBinder(SysDbsource sysDbSource) {
        DataSource dataSource = null;
        try {
            String url = sysDbSource.getUrl();
            String username = sysDbSource.getUserName();
            String password = sysDbSource.getPassword();
            Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName(JdbcUtils.DBSOURCE_NAME);
            DataSourceBuilder<?> factory = DataSourceBuilder.create().driverClassName(JdbcUtils.DRIVER_CLASS_NAME).url(url).username(username).password(password).type(dataSourceType);
            dataSource = DEFAULT_DATASOURCE = factory.build();
            if (BINDER != null) {
                BINDER.bind("spring.datasource.common.pool", Bindable.ofInstance(dataSource));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    private static String getCurrentDataSourceKey(String id) {
        return id + SplitCharEnum.UNDERLINE.getName() + DynamicDataSourceContextHolder.DATA_SOURCE_SUFFIX;
    }

    public static String initCurrentDataSourceValue() {
        String datasourceKey = getCurrentDataSourceKey(UserUtils.getCurrentUserId());
        String value = (String) RedisCacheUtils.getSysCache(datasourceKey);
        if (StringUtils.isEmpty(value)) {
            SysUserDbsourceService sysUserDbsourceService = (SysUserDbsourceService) SpringContextUtils.getBean("sysUserDbsourceService");
            SysUserDbsourceQuery dbsourceQuery = new SysUserDbsourceQuery();
            dbsourceQuery.setUserId(UserUtils.getCurrentUserId());
            SysUserDbsourcePO sysUserDbsourcePO = new SysUserDbsourcePO(dbsourceQuery);
            Response<List<SysUserDbsourceResult>> userDbsourceResultRes = sysUserDbsourceService.findList(sysUserDbsourcePO);
            if (CollectionUtils.isNotEmpty(userDbsourceResultRes.getData())) {
                value = userDbsourceResultRes.getData().get(0).getDbsourceName() + SplitCharEnum.COLON.getName() + UsableEnum.YES.getCode();
                RedisCacheUtils.setSysCache(datasourceKey, value);
            } else {
                SysUserDbsource sysUserDbsource = new SysUserDbsource();
                sysUserDbsource.setUserId(UserUtils.getCurrentUserId());
                sysUserDbsource.setDbsourceId(DbsourceUtils.getSysDbsourceId(DEFAULT_DATASOURCE_KEY));
                sysUserDbsourceService.save(sysUserDbsource);
                value = DEFAULT_DATASOURCE_KEY + SplitCharEnum.COLON.getName() + UsableEnum.YES.getCode();
                RedisCacheUtils.setSysCache(datasourceKey, value);
            }
        }
        return value;
    }

    public static String getCurrentDataSourceValue() {
        String datasourceKey = getCurrentDataSourceKey(UserUtils.getCurrentUserId());
        String values = (String) RedisCacheUtils.getSysCache(datasourceKey);
        if (StringUtils.isEmpty(values)) {
            values = initCurrentDataSourceValue();
        }
        return values.split(SplitCharEnum.COLON.getName())[0];
    }

    public static String getCurrentDataSourceValue(String userId) {
        String datasourceKey = getCurrentDataSourceKey(userId);
        String values = (String) RedisCacheUtils.getSysCache(datasourceKey);
        if (StringUtils.isEmpty(values)) {
            values = initCurrentDataSourceValue();
        }
        return values.split(SplitCharEnum.COLON.getName())[0];
    }

    public static boolean getCurrentDataSourceUsable() {
        String datasourceKey = getCurrentDataSourceKey(UserUtils.getCurrentUserId());
        String values = (String) RedisCacheUtils.getSysCache(datasourceKey);
        if (StringUtils.isEmpty(values)) {
            values = initCurrentDataSourceValue();
        }
        int usable = Integer.valueOf(values.split(SplitCharEnum.COLON.getName())[1]);
        if (usable == UsableEnum.YES.getCode()) {
            return true;
        }
        return false;
    }

    public static void setCurrentDataSourceValue(String value, Integer dbsourceId, int usable) {
        setCurrentDataSourceValue(value, UserUtils.getCurrentUserId(), dbsourceId, usable);
    }

    public static void setCurrentDataSourceValue(String value, String userId, Integer dbsourceId, int usable) {
        SysUserDbsourceService sysUserDbsourceService = (SysUserDbsourceService) SpringContextUtils.getBean("sysUserDbsourceService");
        SysUserDbsourceQuery dbsourceQuery = new SysUserDbsourceQuery();
        dbsourceQuery.setUserId(userId);
        SysUserDbsourcePO sysUserDbsourcePO = new SysUserDbsourcePO(dbsourceQuery);
        Response<List<SysUserDbsourceResult>> userDbsourceResultRes = sysUserDbsourceService.findList(sysUserDbsourcePO);
        if (CollectionUtils.isNotEmpty(userDbsourceResultRes.getData())) {
            SysUserDbsourceResult sysUserDbsourceResult = userDbsourceResultRes.getData().get(0);
            if (!Integer.valueOf(sysUserDbsourceResult.getDbsourceId()).equals(dbsourceId)) {
                SysUserDbsource sysUserDbsource = new SysUserDbsource();
                sysUserDbsource.setId(sysUserDbsourceResult.getId());
                sysUserDbsource.setUserId(userId);
                sysUserDbsource.setDbsourceId(dbsourceId);
                sysUserDbsourceService.update(sysUserDbsource);
                String datasourceKey = getCurrentDataSourceKey(userId);
                RedisCacheUtils.setSysCache(datasourceKey, value + SplitCharEnum.COLON.getName() + usable);
            }
            return;
        }
        throw new BusinessException(BusinessErrorEnum.DB_CONNECT_NOT_EFFECT);
    }
}
