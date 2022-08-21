package com.example.datasources.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义创建线程池
 *
 * @author zhouhui
 * @Description desc
 * @date 2022-08-07 17:35
 */
@Configuration
@EnableAsync
public class TaskExecutePool {
    @Autowired
    private ThreadPoolConfig threadPoolConfig;

    @Bean
    public Executor myTaskAsyncPool() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程池大小
        executor.setCorePoolSize(threadPoolConfig.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(threadPoolConfig.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(threadPoolConfig.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(threadPoolConfig.getKeepAliveSeconds());
        //线程名字前缀
        executor.setThreadNamePrefix("async-Executor-");
        // 拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}


