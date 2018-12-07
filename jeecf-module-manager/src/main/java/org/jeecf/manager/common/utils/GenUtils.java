package org.jeecf.manager.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JaxbMapper;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.common.utils.FileTypeUtils;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.common.utils.FreeMarkers;
import org.jeecf.common.utils.IdGenUtils;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.enums.BusinessErrorEnum;
import org.jeecf.manager.common.model.GenEntity;
import org.jeecf.manager.common.properties.TemplateProperties;
import org.jeecf.manager.gen.model.GenParams;
import org.jeecf.manager.module.config.model.domain.SysNamespace;
import org.jeecf.manager.module.gen.model.gen.SysDictGenEntity;
import org.jeecf.manager.template.GenEnumsSchemeTemplate;
import org.jeecf.manager.template.model.GenSchemaTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author jianyiming
 *
 */
public class GenUtils {

	public static final String[] TYPES = { "enum" };

	private static TemplateProperties properties = SpringContextUtils.getBean(TemplateProperties.class);


	/**
	 * XML文件转换为对象
	 * 
	 * @param fileName
	 * @param clazz
	 * @return
	 */
	public static <T> T xmlToObject(String pathName, Class<T> clazz) {
		Resource resource = new ClassPathResource(pathName);
		InputStream is;
		try {
			is = resource.getInputStream();
			return GenUtils.xmlToObject(is, clazz);
		} catch (IOException e) {
		}
		return null;
	}

	public static <T> T xmlToObject(InputStream is, Class<T> clazz) {
		try {
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
			return JaxbMapper.fromXml(sb.toString(), clazz);
		} catch (IOException e) {
		}
		return null;
	}

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
		} catch (IOException e) {
		}
		return null;
	}

	public static <V extends GenEntity> String create(String type, GenEntity entity) {
		if (type.equals(GenUtils.TYPES[0])) {
			return new GenEnumsSchemeTemplate().create((SysDictGenEntity) entity);
		}
		return null;
	}

	public static boolean generateToFile(GenSchemaTemplate tpl, Map<String, Object> model, String basePath) {
		// 获取生成文件
		String fileName = basePath + File.separator
				+ FreeMarkers.renderString(tpl.getName(), StringUtils.trimToEmpty(tpl.getFilePath()), model)
				+ File.separator
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

	public static String build(List<GenParams> genParamsList, Integer tableId, String sourcePath, Integer language) {
		ChainContext genChainContext = ChainUtils.genChainContext();
		String outZip = sourcePath + File.separator + "code.zip";
		genChainContext.put("params", new HashMap<String, Object>());
		genChainContext.put("genParamsList", genParamsList);
		genChainContext.put("tableId", tableId);
		genChainContext.put("language", language);
		genChainContext.put("sourcePath",sourcePath);
		genChainContext.put("configPath",sourcePath + File.separator + "config.json");
		genChainContext.put("codePath",sourcePath + File.separator + "code");
		genChainContext.put("outZip",outZip);
		genChainContext.next();
		if(genChainContext.isFlag()) {
			return outZip;
		}
		return null;
	}

	public static String upload(MultipartFile file, SysNamespace sysNamespace) {
		boolean isZip = FileTypeUtils.isType(properties.getDownloadSuffixName(),
				SplitCharEnum.DOT.getName() + file.getOriginalFilename());
		if (!isZip) {
			throw new BusinessException(BusinessErrorEnum.ZIP_NOT);
		}
		if (sysNamespace != null) {
			String uuid = IdGenUtils.uuid();
			String suffixPath = properties.getUploadTmpPath() + File.separator + sysNamespace.getName() + File.separator
					+ uuid;
			String filePath = suffixPath + File.separator + file.getOriginalFilename();
			try {
				FileUtils.createDirectory(suffixPath);
				Files.write(Paths.get(filePath), file.getBytes());
				RedisCacheUtils.setSysCache(uuid, file.getOriginalFilename());
				return uuid;
			} catch (IOException e) {
				throw new BusinessException(SysErrorEnum.IO_ERROR);
			}
		}
		throw new BusinessException(BusinessErrorEnum.NAMESPACE_NOT);
	}

}
