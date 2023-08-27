package org.example;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class Generator {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ai_assistant?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai";
        String userName = "root";
        String password = "root";
        String outputDir = "D:\\mybatisPlus";

        FastAutoGenerator.create(url, userName, password)
                .globalConfig(builder -> {
                    builder.author("wuchaoxin") // 设置作者
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(outputDir); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("org.example") // 设置父包名
                            .moduleName("ai.assistant.dal") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, outputDir)); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                    builder.entityBuilder()
                            .enableLombok()
                            .idType(IdType.AUTO)
                            .enableTableFieldAnnotation()
                            .formatFileName("%sPO");
                    builder.serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImp");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}