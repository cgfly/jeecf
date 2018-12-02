package org.jeecf.manager.common.utils;

import java.io.File;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.manager.common.properties.TemplateProperties;

/**
 * 模版文件工具类
 * 
 * @author jianyiming
 *
 */
public class TemplateUtils {

	private static TemplateProperties properties = SpringContextUtils.getBean("templateProperties",
			TemplateProperties.class);

	public static void delDownload(String filePath, String namespaceName) {
		String sourcePath = properties.getUploadPath() + File.separator + namespaceName + File.separator + filePath;
		String tempPath = properties.getUploadTmpPath() + File.separator + namespaceName + File.separator + filePath;
		FileUtils.deleteDirectory(sourcePath);
		FileUtils.deleteDirectory(tempPath);
	}

	public static void unzip(String path, String name, String namespaceName) {
		String tempFilePath = properties.getUploadTmpPath() + File.separator + namespaceName + File.separator + path
				+ File.separator + name;
		String desPath = getUnzipPath(path, namespaceName);
		ZipUtils.unzip(tempFilePath, desPath);
	}

	public static String getUnzipPath(String path, String namespaceName) {
		String sourcePath = properties.getUploadPath() + File.separator + namespaceName + File.separator + path;
		return sourcePath;
	}

	public static String getZipFilePath(String path, String namespaceName) {
		String zipFilePath = properties.getUploadTmpPath() + File.separator + namespaceName + File.separator + path
				+ SplitCharEnum.DOT.getName() + properties.getDownloadSuffixName();
		return zipFilePath;
	}

}
