package org.jeecf.manager.common.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * 插件属性
 * 
 * @author jianyiming
 *
 */
@Data
@Configuration
public class PluginProperties {

    /**
     * 上传文件路径
     */
    @Value("${file.plugin.upload.path}")
    private String uploadPath;

    /**
     * 临时上传文件路径
     */
    @Value("${file.plugin.upload.tmp.path}")
    private String uploadTmpPath;

    /**
     * 文件后缀
     */
    @Value("${file.plugin.suffix}")
    private String suffixName;

}
