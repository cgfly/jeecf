package org.jeecf.manager.common.thymeleaf.expression;

import org.jeecf.manager.common.utils.NamespaceUtils;
import org.jeecf.manager.common.utils.UserUtils;
import org.jeecf.manager.module.config.model.domain.SysNamespace;

/**
 * 命名空间表达式
 * @author jianyiming
 *
 */
public final class Namespaces {
	
	private static final String NO_NAMESPACE = "没有可用命名空间";
	/**
	 * 获取命名空间名
	 * @return
	 */
	public String getName() {
		SysNamespace sysNamespace =	NamespaceUtils.getNamespace(UserUtils.getCurrentUserId());
		if(sysNamespace == null) {
			return NO_NAMESPACE;
		}
		return sysNamespace.getName();
	}

}
