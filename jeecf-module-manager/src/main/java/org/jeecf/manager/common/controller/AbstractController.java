package org.jeecf.manager.common.controller;

import org.springframework.ui.ModelMap;

/**
 * 控制类 基类
 * @author jianyiming
 *
 * @param <Q>
 * @param <R>
 * @param <T>
 */
public abstract class AbstractController {
	
    /**
     * 页面定位
     * @param map
     * @return
     */
	protected abstract String index(ModelMap map);


}
