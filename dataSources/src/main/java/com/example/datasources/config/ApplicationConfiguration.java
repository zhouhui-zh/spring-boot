package com.example.datasources.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-08-12 14:41
 */

@Slf4j
@Data
@Component
public class ApplicationConfiguration {
    @Value("常量")
    String name;

    @Value("${task.pool.corePoolSize}")
    Integer corePoolSize;
    @Value("${task.pool.不存在 :123}")
    Integer corePoolSize22;
    @Value("${task.pool.不存在 ?:12}")
    Integer maxPoolSize2;

    @Value("#{threadPoolConfig.queueCapacity ?:1}")
    String queueCapacity;
    @Value("#{threadPoolConfig.queueCapacity2 ?:'1'}")
    String queueCapacity2;

    @PostConstruct
    public void log() {
        log.info("name={}", name);
        log.info("task.pool.corePoolSize={}", corePoolSize);
        log.info("task.pool.corePoolSize22={}", corePoolSize22);
        log.info("maxPoolSize2={}", maxPoolSize2);
        log.info("queueCapacity={}", queueCapacity);
        log.info("queueCapacity2={}", queueCapacity2);
        System.exit(-1);
    }
}
