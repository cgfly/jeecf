package org.jeecf.manager.interceptor;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.jeecf.common.enums.SysErrorEnum;
import org.jeecf.common.exception.BusinessException;
import org.jeecf.common.model.Response;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

/**
 * 异常处理
 * 
 * @author GloryJian
 *
 */
@ControllerAdvice
@Slf4j
public class WebExceptionHandle {

    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public Response<String> unauthorizedExceptionHandler(UnauthorizedException e) {
        Response<String> response = new Response<String>();
        response.setSuccess(false);
        response.setErrorCode(SysErrorEnum.UNAUTHORIZED_ERROR.getCode());
        response.setErrorMessage(SysErrorEnum.UNAUTHORIZED_ERROR.getMsg());
        log.error(ExceptionUtils.getStackTrace(e));
        return response;
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    @ResponseBody
    public Response<String> mySQLSyntaxErrorException(BadSqlGrammarException e) {
        Response<String> response = new Response<String>();
        response.setSuccess(false);
        response.setErrorCode(SysErrorEnum.DB_ERROR.getCode());
        response.setErrorMessage(e.getSQLException().getMessage());
        log.error(ExceptionUtils.getStackTrace(e));
        return response;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseBody
    public Response<String> constraintViolationException(ConstraintViolationException e) {
        Response<String> response = new Response<String>();
        response.setSuccess(false);
        response.setErrorCode(SysErrorEnum.FIELD_ERROR.getCode());
        Set<ConstraintViolation<?>> set = e.getConstraintViolations();
        set.forEach(violation -> {
            response.setErrorMessage(violation.getMessage());
        });
        log.error(ExceptionUtils.getStackTrace(e));
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Response<String> validateErrorHandler(MethodArgumentNotValidException e) {
        Response<String> response = new Response<String>();
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            String errorMsg = "";
            if (bindingResult.getGlobalError() != null) {
                errorMsg = "错误原因:" + bindingResult.getGlobalError().getDefaultMessage();
            } else {
                List<FieldError> errorList = bindingResult.getFieldErrors();
                errorMsg = "错误原因:" + errorList.get(0).getDefaultMessage();
            }
            response.setSuccess(false);
            response.setErrorCode(SysErrorEnum.FIELD_ERROR.getCode());
            response.setErrorMessage(errorMsg);
        } else {
            response.setSuccess(false);
            response.setErrorCode(SysErrorEnum.SYSTEM_ERROR.getCode());
            response.setErrorMessage(SysErrorEnum.SYSTEM_ERROR.getMsg());
        }
        log.error(ExceptionUtils.getStackTrace(e));
        return response;
    }

    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public Response<String> businessExceptionHandler(BusinessException e) {
        Response<String> response = new Response<String>();
        response.setSuccess(false);
        response.setErrorCode(e.getErrorCode());
        response.setErrorMessage(e.getErrorMsg());
        log.error(ExceptionUtils.getStackTrace(e));
        return response;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public Response<String> exceptionHandler(Throwable t) {
        Response<String> response = new Response<String>();
        response.setSuccess(false);
        response.setErrorCode(SysErrorEnum.SYSTEM_ERROR.getCode());
        response.setErrorMessage(SysErrorEnum.SYSTEM_ERROR.getMsg());
        log.error(ExceptionUtils.getStackTrace(t));
        return response;
    }

}
