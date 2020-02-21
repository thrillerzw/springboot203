package com.example.springboot.api;


import java.io.Serializable;
/**
 * 通用API响应结果封装
 *
 * @param <T>
 */
public class ApiResult<T> implements Serializable{
	private static final long serialVersionUID = 6325204519197175357L;
	/**
	 * 响应码
	 */
	private String resultCode;
	/**
	 * 响应描述
	 */
    private String resultDesc;
    /**
     * 返回数据
     */
    private T data;
   
    public ApiResult(String resultCode, String resultDesc, T data) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.data = data;
    }

    public ApiResult(String resultCode, String resultDesc) {
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
    }

    public ApiResult(){}

    public void setResult(ResultInfoEnum resultInfo) {
        this.resultCode = resultInfo.getResultCode();
        this.resultDesc = resultInfo.getResultDesc();
    }

    public boolean isSuccess() {
        if (ResultInfoEnum.SUCC.getResultCode().equals(resultCode)) {
            return true;
        }
        return false;
    }
    /**
     * 返回成功结果
     * 
     * @return
     */
    public static <T> ApiResult<T> createSuccResult() {
    	ApiResult<T> apiResult = new ApiResult<T>();
    	apiResult.setResult(ResultInfoEnum.SUCC);
        return apiResult;
    }

    /**
     * 返回错误提示
     * 
     * @param resultInfo
     * @return
     */
    public static <T> ApiResult<T> createErrResult(ResultInfoEnum resultInfo) {
    	ApiResult<T> apiResult = new ApiResult<T>();
    	apiResult.setResult(resultInfo);
        return apiResult;
    }
    
    /**
     * 返回成功业务数据
     * 
     * @param data
     * @return
     */
    public static <T> ApiResult<T> createSuccDataResult(T data) {
    	ApiResult<T> apiResult = createSuccResult();
    	apiResult.setData(data);
        return apiResult;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
        this.resultDesc = ResultInfoEnum.getResultDescByCode(resultCode);
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "CommonApiResult [resultCode=" + resultCode + ", resultDesc=" + resultDesc + ", data=" + data + "]";
	}
}
