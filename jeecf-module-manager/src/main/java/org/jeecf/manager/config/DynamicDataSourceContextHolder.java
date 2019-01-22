package org.jeecf.manager.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.manager.common.enums.UsableEnum;
import org.jeecf.manager.common.utils.JdbcUtils;
import org.jeecf.manager.common.utils.RedisCacheUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.config.model.domain.SysDbsource;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

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

    private static ConversionService CONVERSION_SERVICE = new DefaultConversionService();

    private static PropertyValues DATASOURCE_PROPERTYVALUES;

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

    public static ConversionService getConversionService() {
        return CONVERSION_SERVICE;
    }

    public static void setDataSourcePropertyValues(Map<String, Object> rpr) {
        if (DATASOURCE_PROPERTYVALUES == null) {
            Map<String, Object> values = new HashMap<>(rpr);
            DATASOURCE_PROPERTYVALUES = new MutablePropertyValues(values);
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
            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(JdbcUtils.DRIVER_CLASS_NAME).url(url).username(username).password(password).type(dataSourceType);
            dataSource = DEFAULT_DATASOURCE = factory.build();
            if (DATASOURCE_PROPERTYVALUES != null) {
                RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
                dataBinder.setConversionService(CONVERSION_SERVICE);
                dataBinder.setIgnoreNestedProperties(false);
                dataBinder.setIgnoreInvalidFields(false);
                dataBinder.setIgnoreUnknownFields(true);
                dataBinder.bind(DATASOURCE_PROPERTYVALUES);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    private static String getCurrentDataSourceKey() {
        String id = UserUtils.getCurrentUserId();
        return id + SplitCharEnum.UNDERLINE.getName() + DynamicDataSourceContextHolder.DATA_SOURCE_SUFFIX;
    }

    public static void initCurrentDataSourceValue() {
        String datasourceKey = getCurrentDataSourceKey();
        String value = (String) RedisCacheUtils.getSysCache(datasourceKey);
        if (StringUtils.isEmpty(value)) {
            RedisCacheUtils.setSysCache(datasourceKey, DEFAULT_DATASOURCE_KEY + SplitCharEnum.COLON.getName() + UsableEnum.YES.getCode());
        }
    }

    public static String getCurrentDataSourceValue() {
        String datasourceKey = getCurrentDataSourceKey();
        String values = (String) RedisCacheUtils.getSysCache(datasourceKey);
        return values.split(SplitCharEnum.COLON.getName())[0];
    }

    public static boolean getCurrentDataSourceUsable() {
        String datasourceKey = getCurrentDataSourceKey();
        String values = (String) RedisCacheUtils.getSysCache(datasourceKey);
        int usable = Integer.valueOf(values.split(SplitCharEnum.COLON.getName())[1]);
        if (usable == UsableEnum.YES.getCode()) {
            return true;
        }
        return false;
    }

    public static void setCurrentDataSourceValue(String value, int usable) {
        String datasourceKey = getCurrentDataSourceKey();
        RedisCacheUtils.setSysCache(datasourceKey, value + SplitCharEnum.COLON.getName() + usable);
    }
}
