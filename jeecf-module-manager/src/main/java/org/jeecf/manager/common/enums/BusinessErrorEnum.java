package org.jeecf.manager.common.enums;

import org.jeecf.common.enums.ErrorEnum;

/**
 * 系统错误信息枚举
 * 
 * @author jianyiming
 *
 */
public enum BusinessErrorEnum implements ErrorEnum {
	/**
	 * 数据不存在
	 */
	DATA_NOT_EXIT(101, "数据不存在"),
	/**
	 * 数据已存在，请重新输入
	 */
	DATA_EXIT(102, "数据已存在，请重新输入"),
	/**
	 * 插入数据失败
	 */
	INSERT_DATA_FAIL(103, "插入数据失败"),
	/**
	 * 更新数据失败
	 */
	UPDATE_DATA_FAIL(104, "更新数据失败"),
	/** 
	 * 用户没有此权限
	 */  
	POWER_DATA_FAIL(105, "用户没有此权限"),
	/**
	 * 加密失败
	 */
	ENCRYPT_FAIL(106, "加密失败"),
	/**
	 * 解密失败
	 */
	DECRYPT_FAIL(107, "解密失败"),
	/**
	 * 用户不存在
	 */
	USER_NOT(201, "用户不存在"),
	/**
	 * 用户密码输入错误
	 */
	USER_PASSWORD_ERROR(202, "用户密码输入错误"),
	/**
	 * 用户名或密码错误
	 */
	USER_USER_AND_PASSWORD_ERROR(203, "用户名或密码错误"),
	/**
	 * 数据源不存在
	 */
	DARASOURCE_NOT(301, "数据源不存在"),
	/**
	 * 默认的数据源不能删除
	 */
	DARASOURCE_KEY_IS_DEFAULT(302, "默认的数据源不能删除"),
	/**
	 * 当前的数据源不能删除
	 */
	DARASOURCE_KEY_IS_CURRENT(303, "当前的数据源不能删除"),
	/**
	 * 数据库连接异常
	 */
	DB_CONNECT_EXCEPTION(304, "数据库连接异常"),
	/**
	 * 数据源无效
	 */
	DB_CONNECT_NOT_EFFECT(305, "数据源无效"),
	/**
	 * 数据源不可用
	 */
	DB_CONNECT_NOT_USABLE(306, "数据源不可用"),
	/**
	 * 命名空间不存在
	 */
	NAMESPACE_NOT(401, "命名空间不存在"),
	/**
	 * 当前的命名空间不能删除
	 */
	NAMESPACE_IS_CURRENT(402, "当前的命名空间不能删除"),
	/**
	 * 不是zip文件
	 */
	ZIP_NOT(501, "不是zip文件"),
	/**
	 * 不是jar文件
	 */
	JAR_NOT(502, "不是jar文件"),
	/**
	 * config module 不能为空
	 */
	CONFIG_MODULE_MPTY(601, "config module 不能为空"),
	/**
	 * config module name 不能为空
	 */
	CONFIG_MODULE_NAME_EMPTY(602, "config module name 不能为空"),
	/**
	 * config module path 不能为空
	 */
	CONFIG_MODULE_PATH_EMPTY(603, "config module path 不能为空"),
	/**
	 * rule filter type 错误
	 */
	RULE_FILTER_TYPE_ERROR(604, "rule filter type 错误"),
	/**
	 * rule filter value 不能为空
	 */
	RULE_FILTER_VALUE_EMPTY(605, "rule filter value 不能为空"),
	/**
	 * rule filter strategy 错误
	 */
	RULE_FILTER_STRATEGY_ERROR(606, "rule filter strategy 错误"),
	/**
	 * rule strategy name 错误
	 */
	RULE_STRATEGT_NAME_ERROR(607, "rule strategy name 错误"),
	/**
	 * rule strategy type 错误
	 */
	RULE_STRATEGT_TYPE_ERROR(608, "rule strategy type 错误"),
	/**
	 * cofig module has not match rule 错误
	 */
	CONFIG_MODULE_NOT_MATCH_RULE(609, "cofig module has not match rule 错误"),
	/**
	 * 未查到相关表信息
	 */
	RULE_TABLE_PARAM_MANY_NOT_QUERY(610, "未查到相关表信息"),
	/**
	 * 数据分组失败
	 */
	RULE_DATA_GROUP_ERROR(611, "数据分组失败"),
	/**
	 * 树数据构建失败
	 */
	RULE_DATA_TREE_ERROR(612, "树数据构建失败"),
	/**
	 * 规则策略名称不匹配
	 */
	RULE_STRATEGY_NAME_NOT_MATCH(619, "规则策略名称不匹配"),

	/**
	 * rule strategy field 不能为空
	 */
	RULE_STRATEGY_FIELD_NOT_EMPTY(620, "rule strategy field 不能为空"),
	/**
	 * 目标表已经存在
	 */
	TARGET_TABLE_EXIST(701, "目标表已经存在"),
	/**
	 * 目标表不存在
	 */
	TARGET_TABLE_NOT_EXIST(702, "目标表不存在"),
	
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
