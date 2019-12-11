# 返回参数模糊化
```text
1. 在Controller方法添加注解 @Vague(type = Constants.NAME) 说明该方法返回值需要模糊化处理
2. 在返回对象属性添加注解注 @Vague(type = Constants.NAME) 说明该属性值要模糊化处理

 - type 指定模糊化处理方式
```

# resources/META-INF/spring.factories 说明
```text
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\ \
  com.hy.common.config.UnifiedReturnConfig, \
  com.hy.common.config.WebConfig


程序启动加载类配置
```

# 
