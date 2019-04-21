package org.jeecf.manager.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * 权限标签枚举
 * 
 * @author jianyiming
 * @version 2.0
 */
public enum PermissionLabelEnum {

    /**
     * 菜单
     */
    MENU(1, "菜单"),
    /**
     * 命名空间
     */
    NAMESPACE(2, "命名空间"),
    /**
     * 数据源
     */
    DB(3, "数据源"),;

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private PermissionLabelEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Integer getCode(String name) {
        for (PermissionLabelEnum e : PermissionLabelEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (PermissionLabelEnum e : PermissionLabelEnum.values()) {
            if (e.getCode() == code) {
                return e.getName();
            }
        }
        return null;
    }

    public static String toJsonString() {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (PermissionLabelEnum e : PermissionLabelEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(10);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            dataMap.add(map);
        }
        return JsonMapper.toJson(dataMap);
    }

}
