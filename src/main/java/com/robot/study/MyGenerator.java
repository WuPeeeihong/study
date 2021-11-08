package com.robot.study;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.alibaba.druid.sql.parser.Token.BY;

/**
 * @Author Wuph
 * @Date: create in 2021/11/08/ 22:14
 * @Description Mybatis-Plus 代码生成器
 */

public class MyGenerator {

    public static void main(String[] args) {
        //需要生成的表
        List<String> tables = new ArrayList<>();
        tables.add("table_name");


        //Mybatis-Plus 最新版代码生成器
        FastAutoGenerator.create("url", "username", "password")
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
                            .entity("entity")
                            .service("service")
                            .serviceImpl("serviceImpl")
                            .controller("controller")
                            .mapper("mapper")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml,System.getProperty("user.dir")+"\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
//                    StrategyConfig build = new StrategyConfig.Builder()
//                            .entityBuilder()
//                            .superClass(BaseEntity.class)
//                            .disableSerialVersionUID()
//                            .enableChainModel()
//                            .enableLombok()
//                            .enableRemoveIsPrefix()
//                            .enableTableFieldAnnotation()
//                            .enableActiveRecord()
//                            .versionColumnName("version")
//                            .versionPropertyName("version")
//                            .logicDeleteColumnName("deleted")
//                            .logicDeletePropertyName("deleteFlag")
//                            .naming(NamingStrategy.no_change)
//                            .columnNaming(NamingStrategy.underline_to_camel)
//                            .addSuperEntityColumns("id", "created_by", "created_time", "updated_by", "updated_time")
//                            .addIgnoreColumns("age")
//                            .addTableFills(new Column("create_time", FieldFill.INSERT))
//                            .addTableFills(new Property("updateTime", FieldFill.INSERT_UPDATE))
//                            .idType(IdType.AUTO)
//                            .formatFileName("%sEntity")
//                            .build();
//                    builder.addInclude(tables)
//                            .addTablePrefix("lsry_")       //表前缀
//                            .serviceBuilder()
//                            .formatServiceFileName("I%sService")
//                            .formatServiceImplFileName("%sServiceImpl")
//                            .entityBuilder()
//                            .enableLombok()
//                            .logicDeleteColumnName("deleted")
//                            .enableTableFieldAnnotation()
//                            .controllerBuilder()
//                            .formatFileName("%sController")
//                            .enableRestStyle()
//                            .mapperBuilder()
//                            .superClass(BaseMapper.class)
//                            .formatMapperFileName("%sMapper")
//                            .enableMapperAnnotation()
//                            .formatXmlFileName("%sMapper");
                })
                // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

    }
}
