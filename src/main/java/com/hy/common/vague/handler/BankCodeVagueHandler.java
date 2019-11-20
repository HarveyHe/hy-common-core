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
public class BankCodeVagueHandler implements VagueHandler{
    /**
     * 银行卡替换，保留后四位
     *
     * 如果银行卡号为空 或者 null ,返回null ；否则，返回替换后的字符串；
     *
     * @param object 银行卡号
     * @return
     */
    @Override
    public Object handler(Object object) {
        if(object!=null){
            String bankCard=object.toString();
            if (bankCard.isEmpty() || bankCard == null) {
                return null;
            } else {
                return bankCard.substring(0,4)+"**********"+bankCard.substring(bankCard.length()-4,bankCard.length());
            }
        }
        return object;
    }
}
