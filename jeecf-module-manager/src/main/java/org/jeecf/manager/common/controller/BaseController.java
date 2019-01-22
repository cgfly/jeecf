package org.jeecf.manager.common.controller;

import org.springframework.ui.ModelMap;

/**
 * 控制器 基类
 * 
 * @author jianyiming
 *
 */
public interface BaseController {

    /**
     * 页面定位
     * 
     * @param map
     * @return
     */
    public String index(ModelMap map);

}
