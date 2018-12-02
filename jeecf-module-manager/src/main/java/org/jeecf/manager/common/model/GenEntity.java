package org.jeecf.manager.common.model;

/**
 * 代码生成器实体
 * @author jianyiming
 *
 */
public class GenEntity  {
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 生成包路径
	 */
	private String packageName;	
	/**
	 * 功能类型
	 */
	private String functionType;	
	/**
	 * 生成功能名
	 */
	private String functionName;	
	// 
	/**
	 * 生成功能作者
	 */
	private String functionAuthor;	
	/**
	 * 功能版本
	 */
	private String functionVersion;
	
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getFunctionType() {
		return functionType;
	}

	public void setFunctionType(String functionType) {
		this.functionType = functionType;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionAuthor() {
		return functionAuthor;
	}

	public void setFunctionAuthor(String functionAuthor) {
		this.functionAuthor = functionAuthor;
	}

	public String getFunctionVersion() {
		return functionVersion;
	}

	public void setFunctionVersion(String functionVersion) {
		this.functionVersion = functionVersion;
	}
	
}
