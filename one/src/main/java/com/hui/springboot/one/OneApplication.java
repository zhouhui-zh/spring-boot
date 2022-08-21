package com.hui.springboot.one;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//在启动类中添加对mapper包扫描@MapperScan，或者直接在Mapper类上面添加注解@Mapper
@MapperScan(basePackages = {"com.hui.springboot.one.mapper", "com.hui.springboot.one.dao"})
//开启定时任务
@EnableScheduling
public class OneApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneApplication.class, args);
        System.out.println("参考  https://www.cnblogs.com/ityouknow/p/5662753.html");
        System.out.println("参考  https://mp.weixin.qq.com/s?__biz=MzI4NDY5Mjc1Mg==&mid=100000548&idx=1&sn=774cf4802575f58a1e215adae334af2a&chksm=6bf6db5b5c81524dfcb9a1b6d737cffe7d5b070b901afcc3dda2bf06f4379a8876527da1d1d6&scene=18#wechat_redirect");
    }

}
