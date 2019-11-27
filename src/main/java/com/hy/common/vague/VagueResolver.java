package com.hy.common.vague;
/**
  *
  * @Title: VagueResolver
  * @Package: com.hy.common.encrypt
  * @Description: TODO (用一句话描述该文件做什么)
  *
  * @author harvey@xiaotitong.cn
  * @date 2019-11-20 14:53
  * @version V1.0
  * Copyright (c) 2019 XTT Technogolies Co.,Ltd. All Rights Reserved.
  */



import com.hy.common.bean.CommonResult;
import com.hy.common.vague.annotation.Vague;
import com.hy.common.vague.factory.VagueFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;



import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Aspect
@Slf4j
public class VagueResolver  {

   
    
    @Pointcut("@annotation(com.hy.common.vague.annotation.Vague)")
    public void serviceStatistics() {
    
    }
    
    /**
     * 自定义注解处理逻辑
     * 1.通过aop方式拦截自定义注解方法
     * 2.获取到方法返回值
     * 3.判断方法返回值类型
     * 4.对返回值类型进行判断，是否为list<pojo>和pojo
     * 5.将list中pojo或者pojo的属性进行扫描，判断是否使用了自定义注解
     * 6.如果属性使用了注解，根据注解属性值 type 进行相应的处理
     * 7.将修改后的值通过反射的方式放入List<pojo>或者Pojo中，达到修改方法返回值的目的
     * @param joinPoint
     * @param value
     */
    @AfterReturning(value = "@annotation(com.hy.common.vague.annotation.Vague)",returning ="value")
    public void doAfter(JoinPoint joinPoint,Object value) {
        value = this.dataHandle(value);
    }
    
    /**
     * 数据处理
     * 逻辑说明：查询结果只可在pojo中，目前常用返回值类型为pojo，list<pojo> Page,PageInfo Result等
     * 最终实际上都是为pojo，此处后续可以扩展到其余类型
     * 通过一层一层剥离的方式得到pojo进行处理
     * 以Page为例，进入handListType方法后，handListType进行了遍历然后调用dataHandle方法，目的就是让最终dataHandle的入参是pojo
     * 所有后续又新返回类型，可参考一下方式实现
     * @param value
     * @return
     */
    public Object dataHandle(Object value){
        try {

            if(value instanceof ArrayList){
                return this.handListType((List<Object>) value);
            }else if(value instanceof List){
                return this.handListType((List<Object>) value);
            }else if(value instanceof CommonResult){
                CommonResult result= (CommonResult) value;
                return this.dataHandle(result.getResultBody());
            }else {
                return this.handPojoType(value);
            }
        }catch (Exception e){
            return value;
        }
    }
    
    /**
     * 通过反射模糊化处理pojo属性，如果属性是list，继续向下处理，递归逻辑
     * @param object
     * @return
     */
    public Object handPojoType(Object object){
        if (object != null) {
            Class clz =object.getClass();
            // 获取到对象所有属性，并且遍历
            Field[] fields = clz.getDeclaredFields();
            for (Field field : fields) {
                String classType=field.getType().getTypeName();
                boolean fieldHasAnnotation = field.isAnnotationPresent(Vague.class);
                //判断属性上是否有注解，如果有进入逻辑，如果没有，返回对象
                if (fieldHasAnnotation) {
                    //如果属性是String 模糊化他（模糊化处理只能处理String了，不要问为什么）
                    if("java.lang.String".equals(classType)){
                        Vague vague=field.getAnnotation(Vague.class);
                        String type=vague.type();
                        object=this.handleValue(field,object,type);
                    }
                    //这儿就相当于递归处理了，处理对象嵌套对象的方式的模糊化
                    //如果不是，获取到他的值，继续走dataHandle（为什么又要走dataHandle？因为万一他又是List<POJO>这些呢？）
                    else{
                        Class fieldClass =object.getClass();
                        String name=this.firstUpperCase(field.getName());
                        /**
                         * 设置操作权限为true
                         */
                        field.setAccessible(true);
                        Method getMethod= null;
                        try {
                            getMethod = fieldClass.getMethod("get"+name);
                            Object value=getMethod.invoke(object);
                            if(value!=null){
                                value=this.dataHandle(value);
                                Method setMethod=fieldClass.getMethod("set"+name,field.getType());
                                setMethod.invoke(object,value);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return object;
    }
    public Object handListType(List<Object> page){
        if (page != null && !page.isEmpty()) {
            for(int i=0;i<page.size();i++){
                page.set(i, this.dataHandle(page.get(i)));
            }
        }
        return page;
    }

    public Object handleValue(Field field,Object object,String type){
        try {
            Class clz =object.getClass();
            String name=this.firstUpperCase(field.getName());
            /**
             * 设置操作权限为true
             */
            field.setAccessible(true);
            Method getMethod=clz.getMethod("get"+name);
            Object value=getMethod.invoke(object);
            Method setMethod=clz.getMethod("set"+name,field.getType());
            value=this.handleValue(value,type);
            setMethod.invoke(object,value);
            return object;
        }catch (Exception e){
            e.printStackTrace();
            return object;
        }
    }
    public Object handleValue(Object object,String type){
        return VagueFactory.getVagueHandler(type).handler(object);
    }

    public String firstUpperCase(String str){
        return StringUtils.replaceChars(str, str.substring(0, 1),str.substring(0, 1).toUpperCase());
    }
}
