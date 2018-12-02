package org.jeecf.common.enums;

/**
 * 错误信息枚举接口
 * @author jianyiming
 */
public interface ErrorEnum {
    /**
     * 获取错误码
     * @return
     */
	public int getCode();
    /**
     * 获取错误信息
     * @return
     */
	public String getMsg();
}
