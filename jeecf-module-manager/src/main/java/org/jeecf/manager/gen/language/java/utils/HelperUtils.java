package org.jeecf.manager.gen.language.java.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.manager.gen.language.java.model.JavaTableColumn;
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
		typeMap.put("varchar", "String");
		typeMap.put("text", "String");
		typeMap.put("bigint", "Long");
		typeMap.put("integer", "Integer");
		typeMap.put("int", "Integer");
		typeMap.put("tinyint", "Integer");
		typeMap.put("double", "Double");
		typeMap.put("date", "java.util.Date");
		typeMap.put("datetime", "java.util.Date");
		typeMap.put("timestamp", "java.util.Date");
		typeMap.put("decimal", "java.math.BigDecimal");
		return typeMap;
	}
	
	public static String toType(String jdbcType) {
		return HelperUtils.TYPE_MAP.get(jdbcType);
	}
	
	public static List<JavaTableColumn> toColumn(List<GenTableColumnResult> genTableColumnResultList) {
		List<JavaTableColumn> genTableColumnList = new ArrayList<JavaTableColumn>();
		genTableColumnResultList.forEach(tableColumnResult -> {
			JavaTableColumn genTableColumn = new JavaTableColumn();
			BeanUtils.copyProperties(tableColumnResult, genTableColumn);
			String type = HelperUtils.toType(genTableColumn.getSimpleJdbcType());
			genTableColumn.setType(type);
			genTableColumnList.add(genTableColumn);
		});
		return genTableColumnList;
		
	}

}
