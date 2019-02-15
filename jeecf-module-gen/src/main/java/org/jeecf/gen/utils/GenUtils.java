package org.jeecf.gen.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.common.utils.FreeMarkers;
import org.jeecf.gen.model.GenSchemaTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author jianyiming
 * @since 1.0
 */
public class GenUtils {

    public static Iterator<Entry<String, JsonNode>> getConfig(String pathName, String category) {
        try {
            Resource resource = new ClassPathResource(pathName);
            InputStream is = resource.getInputStream();
            JsonNode jsonNode = GenUtils.getConfig(is);
            if (jsonNode != null) {
                return jsonNode.findValue(category).fields();
            }
        } catch (IOException e) {
        }
        return null;
    }

    public static JsonNode getConfig(InputStream is) {
        try {
            if (is != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line).append("\r\n");
                }
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
                return JsonMapper.getJsonNode(sb.toString());
            }
        } catch (IOException e) {
            throw new BusinessException(SysErrorEnum.IO_ERROR);
        }
        return null;
    }

    public static boolean generateToFile(GenSchemaTemplate tpl, Map<String, Object> model, String basePath) {
        // 获取生成文件
        String fileName = basePath + File.separator + FreeMarkers.renderString(tpl.getName(), StringUtils.trimToEmpty(tpl.getFilePath()), model) + File.separator
                + FreeMarkers.renderString(tpl.getName(), StringUtils.trimToEmpty(tpl.getFileName()), model);
        // 获取生成文件内容
        String content = FreeMarkers.renderString(tpl.getName(), StringUtils.trimToEmpty(tpl.getContent()), model);

        // 创建并写入文件
        if (FileUtils.createFile(fileName)) {
            FileUtils.writeToFile(fileName, content, true);
        } else {
            return false;
        }
        return true;
    }

//    public static String build(List<GenParams> genParamsList, String tableName, String sourcePath, Integer language, SysNamespace sysNamespace, SysUser sysUser, List<Plugin> plugins) {
//        ChainContext genChainContext = ChainUtils.genChainContext();
//        String outZip = sourcePath + File.separator + "code.zip";
//        genChainContext.put("params", new HashMap<String, Object>(15));
//        genChainContext.put("genParamsList", genParamsList);
//        genChainContext.put("tableName", tableName);
//        genChainContext.put("language", language);
//        genChainContext.put("sysNamespace", sysNamespace);
//        genChainContext.put("sysUser", sysUser);
//        genChainContext.put("sourcePath", sourcePath);
//        genChainContext.put("configPath", sourcePath + File.separator + "config.json");
//        genChainContext.put("rulePath", sourcePath + File.separator + "rule.json");
//        genChainContext.put("codePath", sourcePath + File.separator + "code");
//        genChainContext.put("outZip", outZip);
//        genChainContext.put("genHandlerPlugin", plugins);
//        genChainContext.next();
//        if (genChainContext.isFlag()) {
//            return outZip;
//        }
//        return null;
//    }

}
