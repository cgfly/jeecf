package org.jeecf.manager.engine.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.engine.dao.BusinessTableDao;
import org.jeecf.manager.engine.model.SelectTable;
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
    
	public String queryAll(SelectTable selectTable){
		List<Map<String,Object >>  result =	businessTableDao.queryAll(selectTable);
		if(CollectionUtils.isNotEmpty(result)) {
			return JsonMapper.toJson(result);
		}
		return null;
	}

}
