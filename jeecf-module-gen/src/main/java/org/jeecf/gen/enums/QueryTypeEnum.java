package org.jeecf.gen.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * 查询类型枚举
 * 
 * @author jianyiming
 *
 */
public enum QueryTypeEnum {
    /**
     * 等于
     */
    EQUALS(0, "="),
    /**
     * 不等于
     */
    NO_EQUALS(1, "!="),
    /**
     * 大于 或 小于
     */
    OPEN_INTERVAL(2, "> or <"),
    /**
     * 大于等于 或 小于等于
     */
    CLOSE_INTERVAL(3, ">= or <="),
    /**
     * like
     */
    LIKE(4, "like"),
    /**
     * right like
     */
    LIKE$(5, "like%"),;

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private QueryTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Integer getCode(String name) {
        for (QueryTypeEnum e : QueryTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (QueryTypeEnum e : QueryTypeEnum.values()) {
            if (e.getCode() == code) {
                return e.getName();
            }
        }
        return null;
    }

    public static String toJsonString() {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (QueryTypeEnum e : QueryTypeEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(10);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            dataMap.add(map);
        }
        return JsonMapper.toJson(dataMap);
    }
}
