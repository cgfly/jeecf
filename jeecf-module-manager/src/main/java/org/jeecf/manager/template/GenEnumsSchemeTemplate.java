package org.jeecf.manager.template;

import java.util.Map;

import org.jeecf.manager.common.utils.GenUtils;
import org.jeecf.manager.module.gen.model.gen.SysDictGenEntity;
import org.springframework.stereotype.Component;
/**
 * 数字字典代码生成模版实现类
 * @author jianyiming
 *
 */
@Component
public class GenEnumsSchemeTemplate extends AbstractGenShemeTemplate<SysDictGenEntity> {

	@Override
	public String getType() {
		return GenUtils.TYPES[0];
	}

	@Override
	public void initParams(Map<String, Object> model, SysDictGenEntity entity) {
		model.put("sysDictGOList", entity.getSysDictGenList());
	}

}
