package com.hy.common.vague.handler;
/**
  *
  * @Title: NameVagueHandler
  * @Package: com.hy.common.vague.handler
  * @Description: TODO (用一句话描述该文件做什么)
  *
  * @author harvey@xiaotitong.cn
  * @date 2019-11-20 15:27
  * @version V1.0
  * Copyright (c) 2019 XTT Technogolies Co.,Ltd. All Rights Reserved.
  */
public class PhoneVagueHandler implements VagueHandler{
    /**
     * 电话长度
     */
    private final int PHONE_NO_LENGTH = 11;
    
    @Override
    public Object handler(Object object) {
        if(object!=null){
            String phoneNo=object.toString();
            if(phoneNo.length() == PHONE_NO_LENGTH){
                String phoneNumber = phoneNo.substring(0, 3) + "****" + phoneNo.substring(7, phoneNo.length());
                return phoneNumber;
            }
        }
        return object;
    }
}
