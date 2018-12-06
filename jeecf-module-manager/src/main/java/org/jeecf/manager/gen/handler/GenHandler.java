package org.jeecf.manager.gen.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.utils.GenUtils;
import org.jeecf.manager.common.utils.ZipUtils;
import org.jeecf.manager.template.model.GenSchemaTemplate;

import com.fasterxml.jackson.databind.JsonNode;
/**
 * 代码生成责任链
 * @author jianyiming
 *
 */
public class GenHandler extends AbstractHandler {

	@Override
	public void init(ChainContext context) {
		this.chainContext = context;
	}

	@Override
	public void process() {
		@SuppressWarnings("unchecked")
		Map<String, Object> params = (Map<String, Object>) this.chainContext.get("params");
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		String sourcePath = (String) this.chainContext.get("sourcePath");
		String configPath = (String) this.chainContext.get("configPath");
		String codePath = (String) this.chainContext.get("codePath");
		String outZip = (String) this.chainContext.get("outZip");
		String outDir =codePath;
		File configFile = new File(configPath);
		try {
			FileInputStream configIs = new FileInputStream(configFile);
			JsonNode node = GenUtils.getConfig(configIs);
			if (node != null) {
				String outBaseDir = node.get("outBaseDir").asText();
				FileUtils.deleteFile(outZip);
				FileUtils.deleteDirectory(codePath);
				if (StringUtils.isNotEmpty(outBaseDir)) {
					outDir = outDir + File.separator + outBaseDir;
				}
				JsonNode pathNode = node.get("path");
				if (pathNode != null) {
					String customParams = null;
					JsonNode paramNode = node.get("params");
					if (paramNode != null) {
						customParams = paramNode.toString();
					}
					Iterator<Entry<String, JsonNode>> iter = node.get("path").fields();
					params.put("customParams", customParams);
					while (iter.hasNext()) {
						Entry<String, JsonNode> entity = iter.next();
						String value = entity.getValue().asText();
						File desFile = new File(sourcePath + File.separator + value);
						FileInputStream desIs = new FileInputStream(desFile);
						GenSchemaTemplate genSchemaTemplate = GenUtils.xmlToObject(desIs, GenSchemaTemplate.class);
						GenUtils.generateToFile(genSchemaTemplate, params, outDir);
					}
					OutputStream out = new FileOutputStream(new File(outZip));
					ZipUtils.toZip(codePath, out, true);
					this.chainContext.put("params",params);
					return;
				}
			}
		} catch (FileNotFoundException e) {
		    throw new BusinessException(SysErrorEnum.FILE_NO);
		}
		this.chainContext.put("params",params);
		this.chainContext.setFlag(false);
	}

}
