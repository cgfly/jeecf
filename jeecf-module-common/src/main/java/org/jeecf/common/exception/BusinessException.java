package org.jeecf.common.exception;

import java.io.Serializable;

import org.jeecf.common.enums.ErrorEnum;

/**
 * 自定义业务异常
 * 
 * @author GloryJ
 *
 */
public class BusinessException extends ThrowException implements Serializable {

    private static final long serialVersionUID = 1L;

    private Class<?> clazz;
    private int errorCode;
    private String errorMsg;
    private Boolean isPrint;

    public BusinessException(int errorCode, String errorMsg) {
        this(errorCode, errorMsg, BusinessException.class);
    }

    public BusinessException(int errorCode, String errorMsg, Class<?> clazz) {
        this(errorCode, errorMsg, clazz, null);
    }

    public BusinessException(int errorCode, String errorMsg, Class<?> clazz, Boolean isPrint) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.clazz = clazz;
        this.isPrint = isPrint;
    }

    public BusinessException(ErrorEnum errorEnum) {
        this(errorEnum.getCode(), errorEnum.getMsg());
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public Boolean getIsPrint() {
        return isPrint;
    }

    public void setIsPrint(Boolean isPrint) {
        this.isPrint = isPrint;
    }

}