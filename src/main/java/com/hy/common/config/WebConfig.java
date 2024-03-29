package com.hy.common.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hy.common.vague.VagueResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
  *
  * @Title: WebMvcConfig
  * @Package: com.hy.common.config
  * @Description:
  *
  * @author harvey@xiaotitong.cn
  * @date 2019-11-20 11:32
  * @version V1.0
  */
@Configuration
public class WebConfig {
    
    
    /**
     * 返回json空值去掉null和""
     * @param builder
     * @return
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }
    
    
    /**
     * 注入 模糊化处理
     * @return
     */
    @Bean
    public VagueResolver vagueResolver(){
        return  new VagueResolver();
    }
}

