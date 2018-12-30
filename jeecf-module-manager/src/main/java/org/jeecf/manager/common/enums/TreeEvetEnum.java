package org.jeecf.manager.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.common.enums.EnumUtils.DelFlag;
/**
 * 树字典 事件枚举
 * @author jianyiming
 *
 */
public enum TreeEvetEnum {
    /**
     * 等于
     */
    EQUALS(1,"等于"),
    /**
     * 不等于
     */
    NO_EQUALS(2,"不等于"),
    /**
     * 大于
     */
    GT(3,"大于"),
    /**
     * 小于
     */
    LT(4,"小于"),
    /**
     * 大于等于
     */
    GT_EQUALS(5,"大于等于"),
    /**
     * 小于等于
     */
    LT_EQUALS(6,"小于等于"),
    /**
     * 与
     */
    AND(7,"与"),
    /**
     * 或
     */
    OR(8,"或"),
    /**
     * 非
     */
    NON(9,"非"),
    ;

	private final int code;
	private final String name;

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	private TreeEvetEnum(int code, String name) {
			this.code = code;
			this.name = name;
		}

	public static Integer getCode(String name) {
		for (DelFlag e : DelFlag.values()) {
			if (e.getName().equals(name)) {
				return e.getCode();
			}
		}
		return null;
	}

	public static String getName(int code) {
		for (DelFlag e : DelFlag.values()) {
			if (e.getCode() == code) {
				return e.getName();
			}
		}
		return null;
	}

	public static String toJsonString() {
		List<Map<String, Object>> dataMap = new ArrayList<>();
		for (DelFlag e : DelFlag.values()) {
			Map<String, Object> map = new HashMap<String, Object>(10);
			map.put("code", e.getCode());
			map.put("name", e.getName());
			dataMap.add(map);
		}
		return JsonMapper.toJson(dataMap);
	}

}
