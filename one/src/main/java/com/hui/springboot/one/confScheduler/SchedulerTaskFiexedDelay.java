package com.hui.springboot.one.confScheduler;

import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class SchedulerTaskFiexedDelay {
    int s = 0;


    @Scheduled(fixedDelay = 6000)
    private void fixedDelayProcess() {
        s++;
        System.out.println();
        System.out.println("fixedDelay 控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，那么直到上一次执行完，\n" +
                "        并间隔给定的时间后，执行下一次");
        System.out.println("fixedDelay现在时间" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        try {
            if (s == 3) {
                System.out.println("fixedDelay  时间直接开始 执行20 s ");
                Thread.sleep(20000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
