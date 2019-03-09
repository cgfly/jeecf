package org.jeecf.manager.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.jeecf.manager.common.utils.JdbcUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
//import org.springframework.boot.bind.RelaxedDataBinder;
//import org.springframework.boot.bind.RelaxedPropertyResolver;
//import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 初始数据源配置
 * 
 * @author jianyiming
 *
 */
public class DataSourceConfiguration implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private DataSource defaultDataSource;
    
    private Binder binder;
    
    @Override
    public void setEnvironment(Environment env) {
        this.binder =  Binder.get(env);
        this.initPrimaryDataSources(env);
        DynamicDataSourceContextHolder.setBinder(this.binder);
    }

    @SuppressWarnings("unchecked")
    private void initPrimaryDataSources(Environment env) {
       
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        Map<String, Object> propertyResolver = binder.bind("spring.datasource.primary", Map.class).get();
        if (defaultDataSource == null) {
            try {
                String url = propertyResolver.get("url").toString();
                String username = propertyResolver.get("username").toString();
                String password = propertyResolver.get("password").toString();
                Class<? extends DataSource> dataSourceType = (Class<? extends DataSource>) Class.forName(JdbcUtils.DBSOURCE_NAME);
                DataSourceBuilder<? extends DataSource> factory = DataSourceBuilder.create().driverClassName(JdbcUtils.DRIVER_CLASS_NAME).url(url).username(username).password(password)
                        .type(dataSourceType);
                defaultDataSource = factory.build();
                binder.bind("spring.datasource.primary.pool", Bindable.ofInstance(defaultDataSource));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>(10);
        // 将主数据源添加到更多数据源中
        targetDataSources.put(DynamicDataSourceContextHolder.getDafualtKey(), defaultDataSource);

        DynamicDataSourceContextHolder.setDefaultDataSource(defaultDataSource);
        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(MultipleDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        // 添加属性：AbstractRoutingDataSource.defaultTargetDataSource
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("multipleDataSource", beanDefinition);

    }

}
