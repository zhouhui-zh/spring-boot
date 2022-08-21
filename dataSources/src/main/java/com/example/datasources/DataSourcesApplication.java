package com.example.datasources;

import com.example.datasources.dbs.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@MapperScan(basePackages = "com.example.datasources.dao")
@Import({DynamicDataSourceRegister.class})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableAsync
@EnableScheduling
public class DataSourcesApplication {

    public static void main(String[] args) {
        System.out.println("https://www.cnblogs.com/feecy/p/11847207.html");
        try {
            SpringApplication.run(DataSourcesApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
