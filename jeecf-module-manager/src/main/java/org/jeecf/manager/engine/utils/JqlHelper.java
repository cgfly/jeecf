package org.jeecf.manager.engine.utils;

import org.jeecf.manager.engine.enums.TableTypeEnum;

public class JqlHelper {

	public static String toJdbcType(int type, int length, int decimalLength) {
		StringBuffer jdbcType = new StringBuffer(TableTypeEnum.getName(type));
		if (TableTypeEnum.INT.getCode() == type) {
			jdbcType.append("(");
			jdbcType.append(length);
			jdbcType.append(")");
		} else if (TableTypeEnum.TINY_INT.getCode() == type) {
			jdbcType.append("(");
			jdbcType.append(length);
			jdbcType.append(")");
		} else if (TableTypeEnum.BIG_INT.getCode() == type) {
			jdbcType.append("(");
			jdbcType.append(length);
			jdbcType.append(")");
		} else if (TableTypeEnum.VARCHAR.getCode() == type) {
			jdbcType.append("(");
			jdbcType.append(length);
			jdbcType.append(")");
		} else if (TableTypeEnum.DECIMAL.getCode() == type) {
			jdbcType.append("(");
			jdbcType.append(length);
			jdbcType.append(",");
			jdbcType.append(decimalLength);
			jdbcType.append(")");
		} 
		return jdbcType.toString();
	}

}
