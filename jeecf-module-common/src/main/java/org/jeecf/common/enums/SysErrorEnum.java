package org.jeecf.common.enums;

/**
 * 系统错误信息枚举
 * @author jianyiming
 */
public enum SysErrorEnum implements ErrorEnum {
	/**
	 * 系统错误
	 */
	SYSTEM_ERROR(100,"系统错误"),
	/**
	 * 数据库错误
	 */
	DB_ERROR(101,"数据库错误"),
	/**
	 * 安全错误
	 */
	SECURITY_ERROR(102,"安全错误"),
	/**
	 * IO错误
	 */
	IO_ERROR(103,"IO错误"),
	/**
	 * 编解码错误
	 */
	ENCODE_ERROR(104,"编解码错误"),
	/**
	 * 格式化错误
	 */
	FORMAT_ERROR(105,"格式化错误"),
	/**
	 * 网络错误
	 */
	NET_ERROR(106,"网络错误"),
	/**
	 * 未被检查错误
	 */
	NOT_CHECK_ERROR(107,"未被检查错误"),
	/**
	 * 字段检查错误
	 */
	FIELD_ERROR(108,"字段检查错误"),
	/**
	 * 文件不存在
	 */
	FILE_NO(109,"文件不存在"),
	/**
	 * 权限不足
	 */
	UNAUTHORIZED_ERROR(403,"权限不足"),
	;

	public final int code;
	public final String msg;

	private SysErrorEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public int getCode() {
		return this.code;
	}

	@Override
	public String getMsg() {
		return this.msg;
	}

}
