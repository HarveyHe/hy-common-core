package com.hy.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
  *
  * @Title: BusinessException
  * @Package: com.hy.common.exception
  * @Description: 业务异常类
  *
  * @author harvey
  * @date 2019-11-20 10:59
  * @version V1.0
  */
@Data
@AllArgsConstructor
public final class BusinessException extends RuntimeException {
    
    private String errorCode;
    
    private String errorMsg;
    
}
