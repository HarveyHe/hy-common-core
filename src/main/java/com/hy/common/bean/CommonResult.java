package com.hy.common.bean;

import lombok.Builder;
import lombok.Data;

/**
  *
  * @Title: CommonResult
  * @Package: com.hy.common.bean
  * @Description: 统一数据返回格式
  *
  * @author harvey
  * @date 2019-11-20 09:57
  * @version V1.0
  */
@Data
@Builder
public final class CommonResult {
    /**
     * 状态码，标识请求成功与否，如 [1:成功；-1:失败]
     */
    private int status = 1;
    /**
     * 错误码，给出明确错误码，更好的应对业务异常；请求成功该值可为空
     */
    private String errorCode = "";
    /**
     * 错误消息，与错误码相对应，更具体的描述异常信息
     */
    private String errorMsg = "";
    /**
     * 返回结果，通常是 Bean 对象对应的 JSON 数据, 通常为了应对不同返回值类型，将其声明为泛型类型
     */
    private Object resultBody;
    
    public CommonResult() {
    }
    
    public CommonResult(Object resultBody) {
        this.resultBody = resultBody;
    }
    
    /**
     * 错误返回值
     * @param errorCode
     * @param errorMsg
     * @return
     */
    public static CommonResult error(String errorCode, String errorMsg){
        return CommonResult.builder().status(-1).errorCode(errorCode).errorMsg(errorMsg).build();
    }
    
    public static CommonResult success(Object resultBody){
        return CommonResult.builder().status(1).resultBody(resultBody).build();
    }
}
