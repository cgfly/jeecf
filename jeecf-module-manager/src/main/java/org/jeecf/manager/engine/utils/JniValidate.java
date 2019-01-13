package org.jeecf.manager.engine.utils;

import org.jeecf.manager.engine.enums.TableTypeEnum;
import org.jeecf.manager.engine.exception.SqlJniException;
import org.jeecf.manager.engine.exception.TableColumnTypeNotExistException;

import com.alibaba.druid.util.StringUtils;

/**
 * sql jni valiedate
 * 
 * @author jianyiming
 *
 */
public class JniValidate {

	private static String[] SPECIAL_CHAR = { "--", ";", "(", ")" };

	public static String columnValidate(String field) {
		if (StringUtils.isEmpty(field)) {
			return field;
		}
		field = field.replace(" ", "");
		for (int i = 0; i < SPECIAL_CHAR.length; i++) {
			if (field.equals(SPECIAL_CHAR[i])) {
				throw new SqlJniException("sql jni column validate not pass");
			}
		}
		return field;
	}

	public static String typeValidate(String type) {
		type = type.replace(" ", "");
		String[] types = type.split("\\(");
        if(!TableTypeEnum.contains(types[0])) {
        	throw new TableColumnTypeNotExistException();
        }
		if (types.length > 1) {
			String[] lengths = types[1].split("\\)");
			if (lengths.length == 1) {
				String[]  inputNumbers = lengths[0].split(",");
				for(String number : inputNumbers) {
					if(!StringUtils.isNumber(number)) {
						 throw new TableColumnTypeNotExistException();
					}
				}
			} else {
               throw new TableColumnTypeNotExistException();
			}
		}
		return type;
	}
	
}
