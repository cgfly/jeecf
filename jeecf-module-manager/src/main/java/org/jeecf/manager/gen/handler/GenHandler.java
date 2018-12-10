package org.jeecf.manager.gen.handler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.utils.FileUtils;
import org.jeecf.manager.common.chain.AbstractHandler;
import org.jeecf.manager.common.chain.ChainContext;
import org.jeecf.manager.common.utils.GenUtils;
import org.jeecf.manager.common.utils.ZipUtils;
import org.jeecf.manager.gen.model.ConfigContext;
import org.jeecf.manager.gen.model.GenSchemaTemplate;
import org.jeecf.manager.gen.model.ModuleEntity;
import org.springframework.core.io.Resource;

/**
 * 代码生成责任链
 * 
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
		String codePath = (String) this.chainContext.get("codePath");
		String outZip = (String) this.chainContext.get("outZip");
		ConfigContext configContext = (ConfigContext) this.chainContext.get("configContext");

		FileUtils.deleteFile(outZip);
		FileUtils.deleteDirectory(codePath);
		OutputStream out;
		try {
			List<ModuleEntity> moduleEntitys = configContext.getModuleEntitys();
			if (CollectionUtils.isNotEmpty(moduleEntitys)) {
				params.put("params", configContext.getGlobalParams());
				for (ModuleEntity moduleEntity : moduleEntitys) {
					Set<Resource> resources = moduleEntity.getPaths();
					String moduleParams = moduleEntity.getModuleParams();
					params.put("moduleParams", moduleParams);
					for (Resource resource : resources) {
						InputStream desIs = resource.getInputStream();
						GenSchemaTemplate genSchemaTemplate = GenUtils.xmlToObject(desIs, GenSchemaTemplate.class);
						GenUtils.generateToFile(genSchemaTemplate, params, configContext.getOutDir());
					}
				}
			}
			out = new FileOutputStream(new File(outZip));
			ZipUtils.toZip(codePath, out, true);
			return;
		} catch (FileNotFoundException e) {
			throw new BusinessException(SysErrorEnum.FILE_NO);
		} catch (IOException e) {
			throw new BusinessException(SysErrorEnum.IO_ERROR);
		}
	}

}
