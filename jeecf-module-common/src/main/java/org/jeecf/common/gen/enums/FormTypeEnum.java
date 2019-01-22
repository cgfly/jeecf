package org.jeecf.common.gen.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.mapper.JsonMapper;

/**
 * form 表单类型
 * 
 * @author jianyiming
 *
 */
public enum FormTypeEnum {
    /**
     * 文本框
     */
    TEXT(0, "文本框"),
    /**
     * 文本域
     */
    AREA(1, "文本域"),
    /**
     * 数字框
     */
    NUMBER(2, "数字框"),
    /**
     * 时间框
     */
    TIME(3, "时间框"),
    /**
     * 下拉框
     */
    SELECT(4, "下拉框"),
    /**
     * 表格下拉框
     */
    TABLE_SELECT(5, "表格下拉框"),;

    private final int code;
    private final String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    private FormTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static Integer getCode(String name) {
        for (FormTypeEnum e : FormTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (FormTypeEnum e : FormTypeEnum.values()) {
            if (e.getCode() == code) {
                return e.getName();
            }
        }
        return null;
    }

    public static String toJsonString() {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (FormTypeEnum e : FormTypeEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(10);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            dataMap.add(map);
        }
        return JsonMapper.toJson(dataMap);
    }

}
