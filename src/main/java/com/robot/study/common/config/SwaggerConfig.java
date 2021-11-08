package com.robot.study.common.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author Wuph
 * @Date: create in 2021/11/08/ 21:21
 * @Description
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi() {
        // 指定扫描包路径
        return new Docket(DocumentationType.SWAGGER_2)
                // 指定生成的文档的类型是Swagger2
                // .pathMapping("/swagger")
                // 用来创建该API的基本信息，展示在文档的页面中（自定义展示的信息）,配置文档页面的基本信息内容
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 扫描所有有注解的api，用这种方式更灵活
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.robot.study.controller"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any()).build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo() {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                // 设置标题
                .title("Study文档")
                // 描述
                .description("Study 学习各模块整合文档")
                //作者信息、联系方式：Contact(String name, String url, String email)
                .contact(new Contact("wuph","https://blog.csdn.net/w1574489152","1574489152@qq.com"))
                // 版本
                .version("版本号: 1.0.1")
                .build();
    }
}
