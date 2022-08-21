package com.hui.springboot.one.confScheduler;

import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class SchedulerTaskFixedRate {

    int ss = 0;

    @Scheduled(fixedRate = 6000)
    private void fixedRateProcess() {
        ss++;
        System.out.println(" fixedRate 是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，下一次也是不会执行，\n" +
                "        但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，而后再按照固定速率继续执行");
        System.out.println("fixedRate现在时间" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
        try {
            if (ss == 3) {
                System.out.println("fixedRate 现在开始 执行20 s ");
                Thread.sleep(20000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
