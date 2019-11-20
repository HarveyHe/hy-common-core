package com.hy.common.config;

import com.hy.common.bean.CommonResult;
import com.hy.common.exception.BusinessException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
  *
  * @Title: UnifiedReturnConfig
  * @Package: com.hy.common.config
  * @Description:
  *
  * @author harvey
  * @date 2019-11-20 10:07
  * @version V1.0
  */
@EnableWebMvc
@Configuration
public class UnifiedReturnConfig {
    
    /**
     * api 统一格式
     */
    @RestControllerAdvice
    static class CommonResultResponseAdvice implements ResponseBodyAdvice<Object> {
        @Override
        public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
            return true;
        }
        
        @Override
        public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
            if (body instanceof CommonResult){
                return body;
            }
            
            return new CommonResult(body);
        }
    }
    
    /**
     * 统一业务异常处理<br>
     * 无论是 Controller 还是 Service 中，只要抛出 BusinessException, 我们都会返回给前端一个统一数据格式
     */
    @RestControllerAdvice
    static class UnifiedExceptionHandler{
        
        @ExceptionHandler(BusinessException.class)
        public CommonResult handleBusinessException(BusinessException be){
            return CommonResult.error(be.getErrorCode(), be.getErrorMsg());
        }
    }
}
