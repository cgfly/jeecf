package org.jeecf.manager.engine.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.engine.dao.BusinessTableDao;
import org.jeecf.manager.engine.model.create.CreateTable;
import org.jeecf.manager.engine.model.index.IndexTable;
import org.jeecf.manager.engine.model.query.SelectTable;
import org.jeecf.manager.engine.utils.JniValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 表 service
 * 
 * @author jianyiming
 *
 */
@Service
public class BusinessTableService {
	
	@Autowired
	private BusinessTableDao businessTableDao;
    
	public String query(SelectTable selectTable){
		List<Map<String,Object >>  result =	businessTableDao.query(selectTable);
		if(CollectionUtils.isNotEmpty(result)) {
			return JsonMapper.toJson(result);
		}
		return null;
	}
	
	public int create(CreateTable createTable){
		return businessTableDao.create(createTable);
	}
	
	public int drop(String tableName){
		return businessTableDao.drop(JniValidate.columnValidate(tableName));
	}
	
	public int addIndex(IndexTable indexTable){
		return businessTableDao.addIndex(indexTable);
	}
}
