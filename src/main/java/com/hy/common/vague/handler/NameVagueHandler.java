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
public class NameVagueHandler implements VagueHandler{
    @Override
    public Object handler(Object object) {
        if(object!=null){
            String userName=object.toString();
            int length=object.toString().length();
            if (length <= 1) {
                return "*";
            } else if (length == 2) {
                return userName.substring(0, 1) + "*";
            }
            else if (length == 3) {
                return userName.substring(0, 1) + "**";
            }
            else{
                StringBuffer sb=new StringBuffer();
                sb.append(userName.substring(0, 2));
                for(int i=0;i<length-2;i++){
                    sb.append("*");
                }
                return sb.toString();
            }
        }
        return object;
    }
}
