package org.jeecf.manager.engine.service;

import java.util.List;

import org.jeecf.common.model.Response;
import org.jeecf.manager.engine.dao.PhysicalTableDao;
import org.jeecf.manager.engine.model.PhysicalTable;
import org.jeecf.manager.engine.model.PhysicalTableColumn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物理表 service
 * 
 * @author jianyiming
 *
 */
@Service
public class PhysicalTableService {
	
	@Autowired
	private PhysicalTableDao physicalTableDao;

	public Response<List<PhysicalTable>> findTableList(PhysicalTable physicalTable){
		return new Response<>(physicalTableDao.findTableList(physicalTable));
	}

	public Response<List<PhysicalTableColumn>> findTableColumnList(PhysicalTableColumn physicalTableColumn){
		return new Response<>(physicalTableDao.findTableColumnList(physicalTableColumn));
	}

}
