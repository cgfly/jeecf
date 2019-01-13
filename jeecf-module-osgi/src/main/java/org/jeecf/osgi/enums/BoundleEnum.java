package org.jeecf.osgi.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * 插件绑定类枚举
 * 
 * @author jianyiming
 *
 */
public enum BoundleEnum {
	/**
	 * 代码生成处理插件
	 */
	GEN_HANDLER_PLUGIN_BOUNDLE(1, "genHandlerPluginBoundle"),;

	private final int code;
	private final String name;

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private BoundleEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public static Integer getCode(String name) {
		for (BoundleEnum e : BoundleEnum.values()) {
			if (e.getName().equals(name)) {
				return e.getCode();
			}
		}
		return null;
	}

	public static String getName(int code) {
		for (BoundleEnum e : BoundleEnum.values()) {
			if (e.getCode() == code) {
				return e.getName();
			}
		}
		return null;
	}
	
	public static String toJsonString() {
		List<Map<String, Object>> dataMap = new ArrayList<>();
		for (BoundleEnum e : BoundleEnum.values()) {
			Map<String, Object> map = new HashMap<String, Object>(10);
			map.put("code", e.getCode());
			map.put("name", e.getName());
			dataMap.add(map);
		}
		return JsonMapper.toJson(dataMap);
	}

}
