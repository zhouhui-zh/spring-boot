package com.hui.springboot.one.confScheduler;

import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Component
public class SchedulerTask {
    private int count = 0;


    @Scheduled(cron = "*/5 * * * * ?")
    private void process() {
        System.out.println();
        System.out.println("定时任务 \t task running " + count);
    }


}
