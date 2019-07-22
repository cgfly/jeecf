package org.jeecf.gen.enums;

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
    TABLE_SELECT(5, "表格下拉框"),
    /**
     * 多选下拉框
     */
    MANY_SELECT(6, "多选下拉框"),
    /**
     * 树形选择框
     */
    TREE_SELECT(7, "树形选择框"),
    /**
     * 标签框
     */
    LABEL_SELECT(8, "标签框"),
    /**
     * 单选按钮
     */
    SELECT_BUTTON(9, "单选按钮"),
    /**
     * 多选按钮
     */
    MANY_SELECT_BUTTON(10, "多选按钮"),
    /**
     * 按钮组
     */
    GROUP_SELECT_BUTTON(11, "按钮组"),;

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
