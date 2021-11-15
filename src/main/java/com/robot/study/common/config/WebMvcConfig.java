package com.robot.study.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author Wuph
 * @Date: create in 2021/11/11/ 17:59
 * @Description
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    //通过重写配置方法覆盖

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/mapper/**").addResourceLocations("classpath:/mapper/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/statics/")
                .addResourceLocations("classpath:/templates/");
        super.addResourceHandlers(registry);
    }
}