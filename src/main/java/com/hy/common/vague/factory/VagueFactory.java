package com.hy.common.vague.factory;

import com.hy.common.vague.Constants;
import com.hy.common.vague.handler.*;

/**
  *
  * @Title: VagueFactory
  * @Package: com.hy.common.vague.factory
  * @Description: TODO (用一句话描述该文件做什么)
  *
  * @author harvey@xiaotitong.cn
  * @date 2019-11-20 16:05
  * @version V1.0
  * Copyright (c) 2019 XTT Technogolies Co.,Ltd. All Rights Reserved.
  */
public class VagueFactory {
    
    private VagueFactory(){
    
    }
    
    public static VagueHandler getVagueHandler(String type){
        switch (type){
            case Constants.NAME:
                return new NameVagueHandler();
            case Constants.ID_CODE:
                return new IdCardVagueHandler();
            case Constants.BANK_CODE:
                return new BankCodeVagueHandler();
            case Constants.ADDRESS:
                return new AddresVagueHandler();
            case Constants.PHONE_NO:
                return new PhoneVagueHandler();
        }
        return new DefaultVagueHandler();
    }
}
