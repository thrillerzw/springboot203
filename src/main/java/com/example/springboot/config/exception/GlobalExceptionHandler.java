package com.example.springboot.config.exception;

import com.example.springboot.api.ApiResult;
import com.example.springboot.api.ResultInfoEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 请求参数校验-普通form表单形式提交
     * @param e
     * @return
     */
    @ExceptionHandler(value = BindException.class)
    public ApiResult bindExceptionHandle(BindException e) {
        FieldError fieldError = e.getFieldError();
        log.error("bindExceptionHandle参数校验异常:{}{}", fieldError.getDefaultMessage(), fieldError.getField(), e);
        ApiResult apiResult = ApiResult.createErrResult(ResultInfoEnum.SYS_EXCEPTION);
        apiResult.setResultDesc(fieldError.getDefaultMessage());
        return apiResult;
    }

    /**
     * 请求参数校验
     * spring MethodValidationInterceptor方式拦截
     * 如果@RequestBody请求时候，@Valid直接写到接口方法，会走这个。
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiResult handleParamValidationException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();
        for(ConstraintViolation constraintViolation:constraintViolations){
            stringBuilder.append(constraintViolation.getMessageTemplate()).append(" ");
        }
        log.error("参数校验错误"+stringBuilder.toString());
        ApiResult apiResult = ApiResult.createErrResult(ResultInfoEnum.SYS_EXCEPTION);
        apiResult.setResultDesc(stringBuilder.toString());
        return apiResult;
    }

    /**
     * 请求参数校验
     * 如果@RequestBody请求时候，@Valid直接写到Controller方法，会走这个
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public <T> ApiResult<T> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        for (FieldError error : bindingResult.getFieldErrors()) {
            String field = error.getField();
            Object value = error.getRejectedValue();
            String msg = error.getDefaultMessage();
            String message = String.format("错误字段:%s，错误值:%s，原因:%s；", field, value, msg);
            stringBuilder.append(message).append(" ");
        }
        log.error("参数校验错误"+stringBuilder.toString());
        ApiResult apiResult = ApiResult.createErrResult(ResultInfoEnum.SYS_EXCEPTION);
        apiResult.setResultDesc(stringBuilder.toString());
        return apiResult;
    }

    @ExceptionHandler(Exception.class)
    public <T> ApiResult<T> handleException(Exception e) {
        log.error("系统异常", e);
        ApiResult apiResult = ApiResult.createErrResult(ResultInfoEnum.SYS_EXCEPTION);
        apiResult.setResultDesc(e.getMessage());
        return apiResult;
    }

}
