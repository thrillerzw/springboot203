package com.example.springboot.config.exception;

import com.example.springboot.api.ResultInfoEnum;

public class ApiException extends RuntimeException{

    public ApiException (ResultInfoEnum resultInfoEnum){
        super(resultInfoEnum.getResultDesc());
        this.resultCode = resultInfoEnum.getResultCode();
        this.resultDesc = resultInfoEnum.getResultDesc();
    }

    /**
     * 响应码
     */
    private String resultCode;
    /**
     * 响应描述
     */
    private String resultDesc;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }
}
