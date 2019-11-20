package com.hy.common.vague.annotation;

/**
 * @author harvey@xiaotitong.cn
 * @version V1.0
 *         Copyright (c) 2019 XTT Technogolies Co.,Ltd. All Rights Reserved.
 * @Title: Vague
 * @Package: com.hy.common.encrypt.annotation
 * @Description: TODO (用一句话描述该文件做什么)
 * @date 2019-11-20 14:51
 */


import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, CONSTRUCTOR, PARAMETER,TYPE})
@Retention(RUNTIME)
@Documented
@Inherited
public @interface Vague {
    String type();
}
