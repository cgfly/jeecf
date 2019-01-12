package org.jeecf.manager.listen;

import org.jeecf.manager.module.config.service.SysDbsourceService;
import org.jeecf.manager.module.extend.service.SysOsgiPluginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
/**
 * 程序监听
 * @author jianyiming
 *
 */
@Component
public class ApplicationEventListener implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private SysDbsourceService sysDbsourceService;
	
	@Autowired
	private SysOsgiPluginService sysOsgiPluginService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		sysDbsourceService.initDbSource();
		sysOsgiPluginService.initPlugin();
	}

}
