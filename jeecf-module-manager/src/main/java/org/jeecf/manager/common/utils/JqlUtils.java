package org.jeecf.manager.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.utils.ReflectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * jql 工具类
 * 
 * @author jianyiming
 *
 */
@Slf4j
public class JqlUtils {

    private static final String SERIAL_VERSION_UID = "serialVersionUID";

    public static <T> void build(Object query, T obj) {
        if (query == null) {
            return;
        }
        List<Field> fieldList = getAllField(obj);
        Field[] fields = query.getClass().getDeclaredFields();
        if (fieldList.size() > 0) {
            for (int i = 0; i < fieldList.size(); i++) {
                Field matchField = fieldList.get(i);
                Field field = JqlUtils.isMatch(matchField, fields, query);
                if (field == null && !SERIAL_VERSION_UID.equals(matchField.getName())) {
                    try {
                        ReflectionUtils.invokeSetter(obj, matchField.getName(), null);
                    } catch (Exception e) {
                        log.warn(e.getMessage());
                    }
                }
            }
        }
    }

    public static <T> void build(Object query, List<T> list) {
        if (query == null) {
            return;
        }
        Field[] fields = query.getClass().getDeclaredFields();
        if (CollectionUtils.isNotEmpty(list)) {
            List<Field> fieldList = getAllField(list.get(0));
            list.forEach(obj -> {
                if (fields.length > 0) {
                    for (int i = 0; i < fieldList.size(); i++) {
                        Field matchField = fieldList.get(i);
                        Field field = JqlUtils.isMatch(matchField, fields, query);
                        if (field == null && !SERIAL_VERSION_UID.equals(matchField.getName())) {
                            try {
                                ReflectionUtils.invokeSetter(obj, matchField.getName(), null);
                            } catch (Exception e) {
                                log.warn(e.getMessage());
                            }
                        }
                    }
                }
            });
        }
    }

    private static Field isMatch(Field field1, Field[] fields, Object query) {
        Field field = null;
        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getType() == boolean.class) {
                Boolean value = (Boolean) ReflectionUtils.getFieldValue(query, fields[i].getName());
                if (value != null && value) {
                    if (field1.getName().equals(fields[i].getName())) {
                        field = field1;
                    }
                }
            }
        }
        return field;
    }

    private static <T> List<Field> getAllField(T obj) {
        List<Field> fieldList = new ArrayList<>();
        Class<?> clazz = obj.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                for (Field field : fields) {
                    fieldList.add(field);
                }
            }
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

}
