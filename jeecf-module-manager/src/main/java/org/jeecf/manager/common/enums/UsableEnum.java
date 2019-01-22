package org.jeecf.manager.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * 可用性
 * 
 * @author jianyiming
 *
 */
public enum UsableEnum {
    /**
     * 可用
     */
    YES(1, "可用"),
    /**
     * 不可用
     */
    NO(2, "不可用"),;

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private UsableEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Integer getCode(String name) {
        for (UsableEnum e : UsableEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (UsableEnum e : UsableEnum.values()) {
            if (e.getCode() == code) {
                return e.getName();
            }
        }
        return null;
    }

    public static String toJsonString() {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (UsableEnum e : UsableEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(10);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            dataMap.add(map);
        }
        return JsonMapper.toJson(dataMap);
    }

}
