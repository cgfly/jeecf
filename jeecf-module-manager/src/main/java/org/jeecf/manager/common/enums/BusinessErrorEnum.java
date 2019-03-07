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
    DATA_NOT_EXIT(1001, "数据不存在"),
    /**
     * 数据已存在，请重新输入
     */
    DATA_EXIT(1002, "数据已存在，请重新输入"),
    /**
     * 插入数据失败
     */
    INSERT_DATA_FAIL(1003, "插入数据失败"),
    /**
     * 更新数据失败
     */
    UPDATE_DATA_FAIL(1004, "更新数据失败"),
    /**
     * 用户没有此权限
     */
    POWER_DATA_FAIL(1005, "用户没有此权限"),
    /**
     * 加密失败
     */
    ENCRYPT_FAIL(1006, "加密失败"),
    /**
     * 解密失败
     */
    DECRYPT_FAIL(1007, "解密失败"),
    /**
     * 用户不存在
     */
    USER_NOT(2001, "用户不存在"),
    /**
     * 用户密码输入错误
     */
    USER_PASSWORD_ERROR(2002, "用户密码输入错误"),
    /**
     * 用户名或密码错误
     */
    USER_USER_AND_PASSWORD_ERROR(2003, "用户名或密码错误"),
    /**
     * 数据源不存在
     */
    DARASOURCE_NOT(3001, "数据源不存在"),
    /**
     * 默认的数据源不能删除
     */
    DARASOURCE_KEY_IS_DEFAULT(3002, "默认的数据源不能删除"),
    /**
     * 当前的数据源不能删除
     */
    DARASOURCE_KEY_IS_CURRENT(3003, "当前的数据源不能删除"),
    /**
     * 数据库连接异常
     */
    DB_CONNECT_EXCEPTION(3004, "数据库连接异常"),
    /**
     * 数据源无效
     */
    DB_CONNECT_NOT_EFFECT(3005, "数据源无效"),
    /**
     * 数据源不可用
     */
    DB_CONNECT_NOT_USABLE(3006, "数据源不可用"),
    /**
     * 命名空间不存在
     */
    NAMESPACE_NOT(4001, "命名空间不存在"),
    /**
     * 当前的命名空间不能删除
     */
    NAMESPACE_IS_CURRENT(4002, "当前的命名空间不能删除"),
    /**
     * 没有有效的命名空间
     */
    NAMESPACE_NO_CURRENT(4003, "没有有效的命名空间"),
    /**
     * 不是zip文件
     */
    ZIP_NOT(5001, "不是zip文件"),
    /**
     * 不是jar文件
     */
    JAR_NOT(5002, "不是jar文件"),
    /**
     * config module 不能为空
     */
    CONFIG_MODULE_MPTY(6001, "config module 不能为空"),
    /**
     * config module name 不能为空
     */
    CONFIG_MODULE_NAME_EMPTY(6002, "config module name 不能为空"),
    /**
     * config module path 不能为空
     */
    CONFIG_MODULE_PATH_EMPTY(6003, "config module path 不能为空"),
    /**
     * rule filter type 错误
     */
    RULE_FILTER_TYPE_ERROR(6004, "rule filter type 错误"),
    /**
     * rule filter value 不能为空
     */
    RULE_FILTER_VALUE_EMPTY(6005, "rule filter value 不能为空"),
    /**
     * rule filter strategy 错误
     */
    RULE_FILTER_STRATEGY_ERROR(6006, "rule filter strategy 错误"),
    /**
     * rule strategy name 错误
     */
    RULE_STRATEGT_NAME_ERROR(6007, "rule strategy name 错误"),
    /**
     * rule strategy type 错误
     */
    RULE_STRATEGT_TYPE_ERROR(6008, "rule strategy type 错误"),
    /**
     * cofig module has not match rule 错误
     */
    CONFIG_MODULE_NOT_MATCH_RULE(6009, "cofig module has not match rule 错误"),
    /**
     * 未查到相关表信息
     */
    RULE_TABLE_PARAM_MANY_NOT_QUERY(6010, "未查到相关表信息"),
    /**
     * 数据分组失败
     */
    RULE_DATA_GROUP_ERROR(6011, "数据分组失败"),
    /**
     * 树数据构建失败
     */
    RULE_DATA_TREE_ERROR(6012, "树数据构建失败"),
    /**
     * 规则策略名称不匹配
     */
    RULE_STRATEGY_NAME_NOT_MATCH(6019, "规则策略名称不匹配"),

    /**
     * rule strategy field 不能为空
     */
    RULE_STRATEGY_FIELD_NOT_EMPTY(6020, "rule strategy field 不能为空"),
    /**
     * distribution strategy not match
     */
    DISTRIBUTION_STRATEGY_NOT_MATCH(6021, "distribution strategy not match"),
    /**
     * distribution type is error
     */
    DISTRIBUTION_TYPE_ERROR(6021, "distribution type is error"),
    /**
     * 目标表已经存在
     */
    TARGET_TABLE_EXIST(7001, "目标表已经存在"),
    /**
     * 目标表不存在
     */
    TARGET_TABLE_NOT_EXIST(7002, "目标表不存在"),
    /**
     * 插件语言不存在
     */
    PLUGIN_LANGUAGE_NOT_EXIST(8001, "plugin language not exist"),
    /**
     * 模版参数不存在
     */
    FIELD_NOT_EXIST(8002, "field not exist");

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
