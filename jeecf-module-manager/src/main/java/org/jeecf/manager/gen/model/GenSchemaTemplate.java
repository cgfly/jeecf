package org.jeecf.manager.gen.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 生成方案Entity
 * @author ThinkGem
 */
@XmlRootElement(name="template")
public class GenSchemaTemplate {
	/**
	 * 模版文件名称
	 */
	private String name; 	
	/**
	 * 生成文件路径
	 */
	private String filePath;
	/**
	 * 文件名
	 */
	private String fileName;	
	/**
	 * 内容
	 */
	private String content;		

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}