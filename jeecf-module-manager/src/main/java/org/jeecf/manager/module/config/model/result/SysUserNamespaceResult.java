package org.jeecf.manager.module.config.model.result;

import java.io.Serializable;

import org.jeecf.manager.module.config.model.domain.SysUserNamespace;
/**
 * 系统用户命名空间返回实体
 * @author jianyiming
 *
 */
public class SysUserNamespaceResult extends SysUserNamespace implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 命名空间名称
	 */
	private String namespaceName;
	/**
	 * 命名空间逻辑删除
	 */
	private Integer namespaceDelFlag;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public Integer getNamespaceDelFlag() {
		return namespaceDelFlag;
	}

	public void setNamespaceDelFlag(Integer namespaceDelFlag) {
		this.namespaceDelFlag = namespaceDelFlag;
	}
	
}
