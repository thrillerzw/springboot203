package com.example.springboot.api;

import java.util.HashMap;
import java.util.Map;

/**
 */
public enum ResultInfoEnum {
    /**
     * 成功
     */
    SUCC("0000", "成功"),
    //系统错误
    SYS_EXCEPTION("0001", "系统异常"),
    SYS_PARAM_ERR("0002", "参数校验错误"),
    SYS_VERIFY_SIGN_ERR("0003", "验签失败"),

    //用户
    USER_NOT_EXIST("0101", "用户不存在"),
    ;

    private ResultInfoEnum(String resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public String getResultCode() {
        return resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    /**
     * 响应码
     */
    private String resultCode;
    /**
     * 响应描述
     */
    private String resultDesc;

    public static String getResultDescByCode(String resultCode) {
        for (ResultInfoEnum resultInfo : ResultInfoEnum.values()) {
            if (resultInfo.getResultCode().equals(resultCode)) {
                return resultInfo.getResultDesc();
            }
        }
        return null;
    }

    public static Map<String, String> wrapResultInfoToMap(ResultInfoEnum resultInfo) {
        Map<String, String> retMap = new HashMap<String, String>();
        retMap.put("resultCode", resultInfo.getResultCode());
        retMap.put("resultInfo", resultInfo.getResultDesc());

        return retMap;
    }

    public static ResultInfoEnum getEnumByCode(String resultCode) {
        for (ResultInfoEnum resultInfo : ResultInfoEnum.values()) {
            if (resultInfo.getResultCode().equals(resultCode)) {
                return resultInfo;
            }
        }
        return null;
    }

}
