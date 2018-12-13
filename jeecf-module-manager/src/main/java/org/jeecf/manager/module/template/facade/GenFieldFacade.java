package org.jeecf.manager.module.template.facade;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.model.Response;
import org.jeecf.manager.module.template.model.domain.GenField;
import org.jeecf.manager.module.template.model.domain.GenFieldColumn;
import org.jeecf.manager.module.template.model.po.GenFieldColumnPO;
import org.jeecf.manager.module.template.model.query.GenFieldColumnQuery;
import org.jeecf.manager.module.template.model.result.GenFieldColumnResult;
import org.jeecf.manager.module.template.model.result.GenFieldResult;
import org.jeecf.manager.module.template.service.GenFieldColumnService;
import org.jeecf.manager.module.template.service.GenFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * genFiled 门面
 * @author jianyiming
 *
 */
@Service
@Transactional(readOnly=true,rollbackFor=RuntimeException.class)
public class GenFieldFacade {
	
	@Autowired
	private GenFieldColumnService genFieldColumnService;
	
	@Autowired
	private GenFieldService genFieldService;
	
	@Transactional(readOnly=false,rollbackFor=RuntimeException.class)
	public Response<GenFieldResult> save(GenField genField) {
		Response<GenFieldResult> genFieldRes = genFieldService.saveByAuth(genField);
		List<GenFieldColumn> fieldColumnList = genField.getGenFieldColumn();
		if(CollectionUtils.isNotEmpty(fieldColumnList)) {
			if(StringUtils.isNotEmpty(genField.getId())) {
				GenFieldColumn genFieldColumn = new GenFieldColumn();
				genFieldColumn.setGenFieldId(Integer.valueOf(genField.getId()));
				genFieldColumnService.delete(genFieldColumn);
			}
			fieldColumnList.forEach(fieldColumn->{
				fieldColumn.setNewRecord(true);
				fieldColumn.setGenFieldId(Integer.valueOf(genField.getId()));
				genFieldColumnService.save(fieldColumn);
			});
		} 
		return genFieldRes;
	}
	
	@Transactional(readOnly=false,rollbackFor=RuntimeException.class)
	public Response<Integer> delete(GenField genField) {
		GenFieldColumnQuery genFieldColumn = new GenFieldColumnQuery();
		genFieldColumn.setGenFieldId(Integer.valueOf(genField.getId()));
		List<GenFieldColumnResult>  fieldColumnList = genFieldColumnService.findList(new GenFieldColumnPO(genFieldColumn)).getData();
		fieldColumnList.forEach(fieldColumn->{
			genFieldColumnService.delete(fieldColumn);
		});
		return genFieldService.deleteByAuth(genField);
	}
   
}
