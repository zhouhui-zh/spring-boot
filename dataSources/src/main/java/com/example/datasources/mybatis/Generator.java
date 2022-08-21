package com.example.datasources.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-07-31 21:47
 */

public class Generator {

    public static void main(String[] args) {
        String   dbUrl = "jdbc:mysql://localhost:3306/test_sharding_proxy_0?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false";
        String outPut = "输出路径";
        FastAutoGenerator.create(dbUrl, "root", "root")
                .globalConfig(builder -> {
                    builder.author("hui") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir") + outPut); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("generator") // 设置父包名
                            .moduleName("dao") // 设置父包模块名
                            .entity("entity") // pojo 实体类包名,其它包名同理
//                            .other("utils") // 自定义文件包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + outPut + "dao/mapper")); // 设置mapperXml生成路径

                })
                // 策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("t_user_0")
                            .entityBuilder()
                            .enableLombok()// 是否使用lombok注解
                            .enableTableFieldAnnotation()// 生成的实体类字段上是否加注解 @TableField("数据库字段名称")
//                            .logicDeleteColumnName("deleted") //逻辑删除字段名
                            .naming(NamingStrategy.underline_to_camel)  //数据库表映射到实体的命名策略：下划线转驼峰命
                            .columnNaming(NamingStrategy.underline_to_camel)    //数据库表字段映射到实体的命名策略：下划线转驼峰命

                            // 添加表字段填充，"created"字段自动填充为插入时间，"modified"字段自动填充为插入修改时间
                            /*.addTableFills(
                                    new Column("created", FieldFill.INSERT),
                                    new Column("modified", FieldFill.INSERT_UPDATE)
                            )*/

                            //通用查询结果列和通用查询映射结果
                            .mapperBuilder()
                            .enableBaseColumnList()// 是否生成<sql id="Base_Column_List">
                            .enableBaseResultMap()// 设置需要生成字段与实体类的映射Map <resultMap id="BaseResultMap" type="实体类全路径">
                            .enableMapperAnnotation()       //开启 @Mapper 注解
                            .serviceBuilder()
                    ; // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();


    }
}
