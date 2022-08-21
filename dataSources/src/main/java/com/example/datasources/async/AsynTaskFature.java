package com.example.datasources.async;


import java.util.concurrent.Future;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;


/**
 * @author zhouhui
 * @Description desc
 * @date 2022-08-07 17:59
 */
@Component
@Slf4j
public class AsynTaskFature {

    @Async
    public Future<String> task1() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(0);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task1任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<String>("task1执行完毕");
    }

    @Async
    public Future<String> task2() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(2000L);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task2任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<String>("task2执行完毕");
    }

    @Async
    public Future<String> task3() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(5000L);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task3任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<String>("task3执行完毕");
    }

    @Async
    public Future<String> task4() throws InterruptedException {
        long currentTimeMillis = System.currentTimeMillis();
        Thread.sleep(3000L);
        long currentTimeMillis1 = System.currentTimeMillis();
        log.info("task4任务耗时:" + (currentTimeMillis1 - currentTimeMillis) + "ms");
        return new AsyncResult<String>("task4执行完毕");
    }


}