package org.jeecf.manager.common.enums;

import org.jeecf.common.enums.ErrorEnum;


/**
 * 系统错误信息枚举
 * @author jianyiming
 *
 */
public enum BusinessErrorEnum implements ErrorEnum {
	
	DATA_NOT_EXIT(101,"数据不存在"),
	DATA_EXIT(102,"数据已存在，请重新输入"),
	INSERT_DATA_FAIL(103,"插入数据失败"),
	UPDATE_DATA_FAIL(104,"更新数据失败"),
    USER_NOT(201,"用户不存在"),
    USER_PASSWORD_ERROR(202,"用户密码输入错误"),
    USER_USER_AND_PASSWORD_ERROR(203,"用户名或密码错误"),
    DARASOURCE_KEY_IS_DEFAULT(301,"默认的数据源不能删除"),
    DARASOURCE_KEY_IS_CURRENT(302,"当前的数据源不能删除"),
    DB_CONNECT_EXCEPTION(303,"数据库连接异常"),
    NAMESPACE_NOT(401,"命名空间不存在"),
    NAMESPACE_IS_CURRENT(402,"当前的命名空间不能删除"),
    ZIP_NOT(501,"不是zip文件"),
    CONFIG_MODULE_NAME_EMPTY(601,"module name 不能为空"),
    CONFIG_MODULE_PATH_EMPTY(602,"module path 不能为空"),
    ;

	public final int code;
	public final String msg;

	private BusinessErrorEnum(int code, String msg) {
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
