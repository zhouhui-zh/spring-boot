package com.example.datasources.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 线程池配置属性类
 *
 * @author zhouhui
 * @Description desc
 * @date 2022-08-07 17:45
 */
@Component
@ConfigurationProperties(prefix = "task.pool")
@Data
public class ThreadPoolConfig {
    private int corePoolSize;

    private int maxPoolSize;

    private int keepAliveSeconds;

    private int queueCapacity = 5;
    private int queueCapacity2 ;
}
