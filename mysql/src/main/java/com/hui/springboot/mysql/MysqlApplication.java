package com.hui.springboot.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@MapperScan(basePackages = "com.hui.springboot.mysql.dao")
public class MysqlApplication {
    private static final transient Logger logger = LoggerFactory.getLogger(MysqlApplication.class);

    static ApplicationContext applicationContext = null;

    public static void main(String[] args) {
        logger.info("start.....");
        applicationContext = SpringApplication.run(MysqlApplication.class, args);
        logger.info("start.....success..");

/*
        System.out.println(applicationContext.getBeanNamesForType(CardDateListDao.class));
        System.out.println(applicationContext.getBean(CardDateListDao.class));
        System.out.println(applicationContext.getBean("cardDateListDao"));
        System.out.println(applicationContext.getType("cardDateListDao"));*/

    }

}
