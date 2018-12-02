package org.jeecf.manager.gen.language.go.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.manager.gen.language.go.model.GenTableColumn;
import org.jeecf.manager.module.template.model.result.GenTableColumnResult;
import org.springframework.beans.BeanUtils;

/**
 * java 语言帮助工具
 * @author jianyiming
 *
 */
public class HelperUtils {
	
	private static final Map<String,String> TYPE_MAP = getTypeMap();
	
	private static Map<String,String> getTypeMap(){
		Map<String,String> typeMap = new HashMap<>(20);
		typeMap.put("varchar", "string");
		typeMap.put("text", "string");
		typeMap.put("bigint", "int64");
		typeMap.put("integer", "int32");
		typeMap.put("int", "int32");
		typeMap.put("tinyint", "int8");
		typeMap.put("double", "float64");
		typeMap.put("date", "time.Time");
		typeMap.put("datetime", "time.Time");
		typeMap.put("timestamp", "time.Time");
		typeMap.put("decimal", "float64");
		return typeMap;
	}
	
	public static String toType(String jdbcType) {
		return HelperUtils.TYPE_MAP.get(jdbcType);
	}
	
	public static List<GenTableColumn> toColumn(List<GenTableColumnResult> genTableColumnResultList) {
		List<GenTableColumn> genTableColumnList = new ArrayList<GenTableColumn>();
		genTableColumnResultList.forEach(tableColumnResult -> {
			GenTableColumn genTableColumn = new GenTableColumn();
			BeanUtils.copyProperties(tableColumnResult, genTableColumn);
			String type = HelperUtils.toType(genTableColumn.getSimpleJdbcType());
			genTableColumn.setType(type);
			genTableColumnList.add(genTableColumn);
		});
		return genTableColumnList;
		
	}

}
