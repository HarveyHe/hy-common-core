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
public class IdCardVagueHandler implements VagueHandler{
    /**
     * 身份证号替换，保留前四位和后四位
     *
     * 如果身份证号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param object 身份证号
     * @return
     */
    @Override
    public Object handler(Object object) {
        if(object!=null){
            String idCode=object.toString();
            if (idCode.isEmpty() || idCode == null) {
                return object;
            } else {
                return idCode.substring(0,4)+"**********"+idCode.substring(idCode.length()-4,idCode.length());
            }
        }
        return object;
    }
}
