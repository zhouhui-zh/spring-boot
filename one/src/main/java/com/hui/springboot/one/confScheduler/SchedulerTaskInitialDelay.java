package com.hui.springboot.one.confScheduler;

import org.apache.http.client.utils.DateUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SchedulerTaskInitialDelay {

    int ss = 0;

    @Scheduled(initialDelay = 3000, fixedDelay = 7000)
    private void pProcess() {
        System.out.println("initialDelay 开始执行 " + ss + " 现在时间" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

    }
}
