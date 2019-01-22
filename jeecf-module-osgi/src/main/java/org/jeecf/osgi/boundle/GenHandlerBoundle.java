package org.jeecf.osgi.boundle;

import org.jeecf.osgi.plugin.GenHandlerPlugin;

/**
 * 代码生成处理插件 绑定类
 * 
 * @author jianyiming
 *
 */
public class GenHandlerBoundle extends BaseBoundle {

    @Override
    public Class<?> getClasses() {
        return GenHandlerPlugin.class;
    }

}
