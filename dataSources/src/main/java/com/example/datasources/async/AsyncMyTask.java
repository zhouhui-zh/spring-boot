package com.example.datasources.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-08-07 17:39
 */

@Component
@Slf4j
public class AsyncMyTask {

    // 注解中标注使用的线程池是哪一个
    // myTaskAsynPool即配置线程池的方法名，此处如果不写自定义线程池的方法名，会使用默认的线程池
    @Async("myTaskAsyncPool")
    public void doTask(int i) throws InterruptedException {
        log.info("Task-Native" + i + " started.");
    }
}

