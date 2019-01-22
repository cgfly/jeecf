package org.jeecf.manager.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.utils.FileTypeUtils;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.common.utils.IdGenUtils;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.properties.TemplateProperties;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.springframework.web.multipart.MultipartFile;

/**
 * 模版文件工具类
 * 
 * @author jianyiming
 *
 */
public class TemplateUtils {

    private static TemplateProperties PROPERTIES = SpringContextUtils.getBean("templateProperties", TemplateProperties.class);

    public static void delDownload(String filePath, String namespaceName) {
        String sourcePath = PROPERTIES.getUploadPath() + File.separator + namespaceName + File.separator + filePath;
        String tempPath = PROPERTIES.getUploadTmpPath() + File.separator + namespaceName + File.separator + filePath;
        FileUtils.deleteDirectory(sourcePath);
        FileUtils.deleteDirectory(tempPath);
    }

    public static void unzip(String path, String name, String namespaceName) {
        String tempFilePath = PROPERTIES.getUploadTmpPath() + File.separator + namespaceName + File.separator + path + File.separator + name;
        String desPath = getUnzipPath(path, namespaceName);
        ZipUtils.unzip(tempFilePath, desPath);
    }

    public static String getUnzipPath(String path, String namespaceName) {
        String sourcePath = PROPERTIES.getUploadPath() + File.separator + namespaceName + File.separator + path;
        return sourcePath;
    }

    public static String getZipFilePath(String path, String namespaceName) {
        String zipFilePath = PROPERTIES.getUploadTmpPath() + File.separator + namespaceName + File.separator + path + SplitCharEnum.DOT.getName() + PROPERTIES.getSuffixName();
        return zipFilePath;
    }

    public static String upload(MultipartFile file, SysNamespace sysNamespace) {
        boolean isZip = FileTypeUtils.isType(PROPERTIES.getSuffixName(), file.getOriginalFilename());
        if (!isZip) {
            throw new BusinessException(BusinessErrorEnum.ZIP_NOT);
        }
        if (sysNamespace != null) {
            int randomNumber = 5;
            String uuid = IdGenUtils.randomUUID(randomNumber);
            String suffixPath = PROPERTIES.getUploadTmpPath() + File.separator + sysNamespace.getName() + File.separator + uuid;
            String filePath = suffixPath + File.separator + file.getOriginalFilename();
            try {
                FileUtils.createDirectory(suffixPath);
                Files.write(Paths.get(filePath), file.getBytes());
                return uuid + File.separator + file.getOriginalFilename();
            } catch (IOException e) {
                throw new BusinessException(SysErrorEnum.IO_ERROR);
            }
        }
        throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
    }

    public static String getDownloadPath(String sourcePath) {
        return sourcePath + File.separator + "code.zip";
    }

}
