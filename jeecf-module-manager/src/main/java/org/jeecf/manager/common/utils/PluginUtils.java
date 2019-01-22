package org.jeecf.manager.common.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.utils.FileTypeUtils;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.properties.PluginProperties;
import org.springframework.web.multipart.MultipartFile;

/**
 * 插件工具类
 * 
 * @author jianyiming
 *
 */
public class PluginUtils {

    private static final PluginProperties PROPERTIES = SpringContextUtils.getBean("pluginProperties", PluginProperties.class);

    public static String getFilePath(String fileName) {
        return PROPERTIES.getUploadPath() + File.separator + fileName;
    }

    public static String getTmpFilePath(String fileName) {
        return PROPERTIES.getUploadTmpPath() + File.separator + fileName;
    }

    public static void delFile(String fileName) {
        String path = getFilePath(fileName);
        FileUtils.deleteFile(path);
    }

    public static void delTmpFile(String fileName) {
        String path = getTmpFilePath(fileName);
        FileUtils.deleteFile(path);
    }

    public static String upload(MultipartFile file) {
        boolean isJar = FileTypeUtils.isType(PROPERTIES.getSuffixName(), file.getOriginalFilename());
        if (!isJar) {
            throw new BusinessException(BusinessErrorEnum.JAR_NOT);
        }
        String filePath = PROPERTIES.getUploadTmpPath() + File.separator + file.getOriginalFilename();
        try {
            FileUtils.createDirectory(PROPERTIES.getUploadTmpPath());
            Files.write(Paths.get(filePath), file.getBytes());
            return filePath;
        } catch (IOException e) {
            throw new BusinessException(SysErrorEnum.IO_ERROR);
        }
    }

}
