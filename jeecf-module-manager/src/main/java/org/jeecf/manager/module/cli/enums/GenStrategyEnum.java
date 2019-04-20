package org.jeecf.manager.module.cli.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;
import org.jeecf.manager.common.enums.ActionTypeEnum;

/**
 * 策略枚举
 */
public enum GenStrategyEnum {

    SINGLE(1, "single");

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private GenStrategyEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Integer getCode(String name) {
        for (ActionTypeEnum e : ActionTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (ActionTypeEnum e : ActionTypeEnum.values()) {
            if (e.getCode() == code) {
                return e.getName();
            }
        }
        return null;
    }

    public static String toJsonString() {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (ActionTypeEnum e : ActionTypeEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(10);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            dataMap.add(map);
        }
        return JsonMapper.toJson(dataMap);
    }

}
