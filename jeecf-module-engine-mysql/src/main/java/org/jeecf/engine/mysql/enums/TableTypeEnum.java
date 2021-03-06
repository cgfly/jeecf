package org.jeecf.engine.mysql.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jeecf.common.lang.StringUtils;
import org.jeecf.common.mapper.JsonMapper;


/**
 * jdbc 类型枚举
 * 
 * @author jianyiming
 *
 */
public enum TableTypeEnum {

    /**
     * tinyint
     */
    TINY_INT(1, "tinyint"),

    /**
     * int
     */
    INT(2, "int"),
    /**
     * bigint
     */
    BIG_INT(3, "bigint"),

    /**
     * decimal
     */
    DECIMAL(4, "decimal"),
    /**
     * varchar
     */
    VARCHAR(5, "varchar"),
    /**
     * text
     */
    TEXT(6, "text"),
    /**
     * datetime
     */
    DATE_TIME(7, "datetime"),
    /**
     * timestamp
     */
    TIME_STAMP(8, "timestamp"),;

    public final int code;
    public final String name;

    private TableTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static boolean contains(String name) {
        name = StringUtils.lowerCase(name);
        TableTypeEnum[] jdbcTypeEnums = TableTypeEnum.values();
        for (TableTypeEnum jdbcTypeEnum : jdbcTypeEnums) {
            if (name.equals(jdbcTypeEnum.getName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean containsWithNumber(String name) {
        name = StringUtils.lowerCase(name);
        if (TableTypeEnum.INT.getName().equals(name)) {
            return true;
        } else if (TableTypeEnum.TINY_INT.getName().equals(name)) {
            return true;
        } else if (TableTypeEnum.BIG_INT.getName().equals(name)) {
            return true;
        }
        return false;
    }

    public static boolean containsWithFloat(String name) {
        name = StringUtils.lowerCase(name);
        if (TableTypeEnum.DECIMAL.getName().equals(name)) {
            return true;
        }
        return false;
    }

    public static boolean containsWithStr(String name) {
        name = StringUtils.lowerCase(name);
        if (TableTypeEnum.VARCHAR.getName().equals(name)) {
            return true;
        } else if (TableTypeEnum.TEXT.getName().equals(name)) {
            return true;
        }
        return false;
    }

    public static boolean containsWithDate(String name) {
        name = StringUtils.lowerCase(name);
        if (TableTypeEnum.DATE_TIME.getName().equals(name)) {
            return true;
        } else if (TableTypeEnum.TIME_STAMP.getName().equals(name)) {
            return true;
        }
        return false;
    }

    public static Integer getCode(String name) {
        for (TableTypeEnum e : TableTypeEnum.values()) {
            if (e.getName().equals(name)) {
                return e.getCode();
            }
        }
        return null;
    }

    public static String getName(int code) {
        for (TableTypeEnum e : TableTypeEnum.values()) {
            if (e.getCode() == code) {
                return e.getName();
            }
        }
        return null;
    }

    public static String toJsonString() {
        List<Map<String, Object>> dataMap = new ArrayList<>();
        for (TableTypeEnum e : TableTypeEnum.values()) {
            Map<String, Object> map = new HashMap<String, Object>(10);
            map.put("code", e.getCode());
            map.put("name", e.getName());
            dataMap.add(map);
        }
        return JsonMapper.toJson(dataMap);
    }

}
