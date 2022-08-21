package com.example.datasources.job;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.datasources.service.impl.TUser1ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.DateTimeException;
import java.util.Date;

/**
 * @author zhouhui
 * @Description desc
 * @date 2022-08-06 21:28
 */

@Component
@Slf4j
public class PlanJob {
    @Autowired
    TUser1ServiceImpl tUser1Service;

    @Scheduled(cron = "0/2 * * * * ?")
    public void info() {
        log.info("执行 开始 {}", new Date());
        tUser1Service.getBaseMapper().selectList(new QueryWrapper<>());
        log.info("执行 结束 {}", new Date());

    }
}
