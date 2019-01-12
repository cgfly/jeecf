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
	@Value("${file.tmpl.download.prefix.path}")
	private String downloadPerfixPath;
	/**
	 * 文件后缀
	 */
	@Value("${file.tmpl.suffix}")
	private String suffixName;
	/**
	 * 上传文件路径       
	 */
	@Value("${file.tmpl.upload.path}")
	private String uploadPath;
	/**
	 * 上传文件原路径
	 */
	@Value("${file.tmpl.upload.tmp.path}")
	private String uploadTmpPath;

}
