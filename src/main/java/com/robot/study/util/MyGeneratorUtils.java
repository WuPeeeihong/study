package com.robot.study.util;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @Author Wuph
 * @Date: create in 2021/11/08/ 22:14
 * @Description Mybatis-Plus 代码生成器
 */
public class MyGeneratorUtils {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/study";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "123456";

    public static void main(String[] args) {
        //需要生成的表
        List<String> tables = new ArrayList<>();
        tables.add("t_menu");
//        tables.add("t_role");

        createGenerator(tables, URL, USERNAME, PASSWORD, "t_");
    }

    /**
     * 传入特定空间表，自动生成代码
     * @param tables    表空间List
     * @param url       数据库链接
     * @param username  用户名
     * @param password  密码
     * @param tablePrefix   表前缀
     */
    private static void createGenerator(List<String> tables, String url, String username, String password, String tablePrefix){
        //Mybatis-Plus 最新版代码生成器
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Wuph") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .commentDate("yyyy-MM-dd")
                            .outputDir(System.getProperty("user.dir") + "\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.robot")
                            .moduleName("study")
                            .entity("bean.entity")
                            .service("service")
                            .serviceImpl("service.impl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir")+"\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            .addTablePrefix(tablePrefix)       //表前缀
                            .serviceBuilder()
                            .formatServiceFileName("I%sService")
                            .superServiceClass(IService.class)
                            .formatServiceImplFileName("%sServiceImpl")
                            .superServiceImplClass(ServiceImpl.class)
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))
//                            .addTableFills(new Property("modifyTime", FieldFill.INSERT_UPDATE))
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
//                            .enableHyphenStyle()
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
