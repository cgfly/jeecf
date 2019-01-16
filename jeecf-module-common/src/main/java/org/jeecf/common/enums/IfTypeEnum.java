package org.jeecf.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * 条件类型枚举
 * @author jianyiming
 *
 */
public enum IfTypeEnum {
	
	/**
	 * 否
	 */
	NO(0, "否"),
	/**
	 * 是
	 */
	YES(1, "是"),;

	private final int code;
	private final String name;

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private IfTypeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Integer getCode(String name) {
		for (IfTypeEnum e : IfTypeEnum.values()) {
			if (e.getName().equals(name)) {
				return e.getCode();
			}
		}
		return null;
	}

	public static String getName(int code) {
		for (IfTypeEnum e : IfTypeEnum.values()) {
			if (e.getCode() == code) {
				return e.getName();
			}
		}
		return null;
	}

	public static String toJsonString() {
		List<Map<String, Object>> dataMap = new ArrayList<>();
		for (IfTypeEnum e : IfTypeEnum.values()) {
			Map<String, Object> map = new HashMap<String, Object>(10);
			map.put("code", e.getCode());
			map.put("name", e.getName());
			dataMap.add(map);
		}
		return JsonMapper.toJson(dataMap);
	}

}
