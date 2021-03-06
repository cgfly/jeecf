package org.jeecf.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * 逻辑删除枚举
 * 
 * @author jianyiming
 *
 */
public enum DelFlagEnum {
    /**
     * 正常
     */
    NO(0, "正常"),
    /**
     * 删除
     */
    YES(1, "删除"),
    /**
     * 所有
     */
    ALL(-1, "所有");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private DelFlagEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Integer getCode(String name) {
        for (DelFlagEnum e : DelFlagEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (DelFlagEnum e : DelFlagEnum.values()) {
            if (e.getCode() == code) {
                return e.getName();
            }
        }
        return null;
    }

    public static String toJsonString() {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (DelFlagEnum e : DelFlagEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(10);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            dataMap.add(map);
        }
        return JsonMapper.toJson(dataMap);
    }

}
