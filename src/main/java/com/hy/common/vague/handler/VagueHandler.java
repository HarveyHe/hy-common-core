package com.hy.common.vague.handler;

/**
 * @author harvey@xiaotitong.cn
 * @version V1.0
 *         Copyright (c) 2019 XTT Technogolies Co.,Ltd. All Rights Reserved.
 * @Title: VagueHandler
 * @Package: com.hy.common.vague.annotation
 * @Description: TODO (用一句话描述该文件做什么)
 * @date 2019-11-20 15:25
 */
public interface VagueHandler {
    
    /**
     * @封装处理
     * @param object 需要处理的对象
     * @return 处理后的对象
     */
    public Object handler(Object object);
}
