package org.jeecf.manager.module.gen.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.common.properties.DictProperties;
import org.jeecf.manager.common.service.NamespaceAuthService;
import org.jeecf.manager.common.utils.GenUtils;
import org.jeecf.manager.module.gen.dao.SysDictDao;
import org.jeecf.manager.module.gen.model.domian.SysDict;
import org.jeecf.manager.module.gen.model.gen.SysDictGen;
import org.jeecf.manager.module.gen.model.gen.SysDictGenEntity;
import org.jeecf.manager.module.gen.model.po.SysDictPO;
import org.jeecf.manager.module.gen.model.query.SysDictQuery;
import org.jeecf.manager.module.gen.model.result.SysDictResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据字典
 * 
 * @author GloryJian
 *
 */
@Service
public class SysDictService extends NamespaceAuthService<SysDictDao, SysDictPO,SysDictResult,SysDictQuery, SysDict> {

	@Autowired
	private DictProperties genDictProperties;

	public Response<String> genCreate() {

		SysDictGenEntity sysDictGenEntity = new SysDictGenEntity();
		sysDictGenEntity.setPackageName(genDictProperties.getJavaPackageName());
		sysDictGenEntity.setFunctionAuthor(genDictProperties.getJavaFunctionAuthor());
		sysDictGenEntity.setFunctionName(genDictProperties.getJavaFunctionName());
		sysDictGenEntity.setFunctionVersion(genDictProperties.getJavaFunctionVersion());
		sysDictGenEntity.setFunctionType(GenUtils.TYPES[0]);
		List<SysDictResult> sysDictColumnList = super.findListByAuth(new SysDictPO(new SysDictQuery())).getData();
		List<SysDictGen> sysDictGenList = new ArrayList<SysDictGen>();
		if (CollectionUtils.isNotEmpty(sysDictColumnList)) {
			Map<String, List<SysDict>> sysDictByName = sysDictColumnList.stream()
					.collect(Collectors.groupingBy(SysDict::getType));
			for (Map.Entry<String, List<SysDict>> entry : sysDictByName.entrySet()) {
				SysDictGen sysDictGen = new SysDictGen();
				List<SysDict> sysDictList = entry.getValue();
				sysDictGen.setType(entry.getKey());
				sysDictGen.setDescription(sysDictList.get(0).getDescription());
				sysDictGen.setSysDictList(sysDictList);
				sysDictGenList.add(sysDictGen);
			}
		}
		sysDictGenEntity.setSysDictGOList(sysDictGenList);
		
		String basePath = GenUtils.create(sysDictGenEntity.getFunctionType(), sysDictGenEntity);
		return new Response<String>(basePath);
	}
}