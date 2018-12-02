package org.jeecf.manager.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
/**
 * 模版属性
 * @author jianyiming
 *
 */
@Data
@Configuration
public class TemplateProperties {
	/**
	 * 下载文件路径前缀
	 */
	@Value("${template.download.prefix.path}")
	private String downloadPerfixPath;
	/**
	 * 下载文件路径后缀
	 */
	@Value("${template.download.suffix.name}")
	private String downloadSuffixName;
	/**
	 * 上传文件路径       
	 */
	@Value("${template.upload.path}")
	private String uploadPath;
	/**
	 * 上传文件原路径
	 */
	@Value("${template.upload.tmp.path}")
	private String uploadTmpPath;

}
