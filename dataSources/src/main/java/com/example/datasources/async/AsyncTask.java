package com.example.datasources.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-08-07 17:23
 */

@Component
@Slf4j
public class AsyncTask {

    // 直接使用该注解即可
    @Async
    public void doTask(int i) throws InterruptedException {
        log.info("Task-Native" + i + " started.");
    }
}
