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
    @Override
    public Object handler(Object object) {
        if(object!=null){
            String phone_no=object.toString();
            if(phone_no.length()==11){
                String phoneNumber = phone_no.substring(0, 3) + "****" + phone_no.substring(7, phone_no.length());
                return phoneNumber;
            }
        }
        return object;
    }
}
