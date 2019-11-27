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
    
    /**
     * 名字长度
     */
    private final int NAME_LENGTH1 = 1;
    private final int NAME_LENGTH2 = 2;
    private final int NAME_LENGTH3 = 3;
    
    /**
     * 字符串前保留 n 个字节
     */
    private final int PREFIX_KEEP_BYTE = 2;
    /**
     * 字符串后保留 n 个字节
     */
    private final int SUFFIX_KEEP_BYTE = 2;
    
    
    
    @Override
    public Object handler(Object object) {
        if(object!=null){
            String userName=object.toString();
            int length=object.toString().length();
            if (length <= NAME_LENGTH1) {
                return "*";
            } else if (length == NAME_LENGTH2) {
                return userName.substring(0, 1) + "*";
            }
            else if (length == NAME_LENGTH3) {
                return userName.substring(0, 1) + "**";
            }
            else{
                StringBuffer sb=new StringBuffer();
                sb.append(userName.substring(0, PREFIX_KEEP_BYTE));
                for(int i=0;i< length-SUFFIX_KEEP_BYTE;i++){
                    sb.append("*");
                }
                return sb.toString();
            }
        }
        return object;
    }
}
