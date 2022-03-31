package com.mzl.incomeexpensemanagesystem1.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName :   TimeJob
 * @Description: 定时任务
 * @Author: v_ktlema
 * @CreateDate: 2022/3/31 11:04
 * @Version: 1.0
 */
@Component
@Slf4j
public class TimeJob {

    /**
     * 清除Linux生成的文件的定时任务，每天23:59:59秒开始清除今天的文件，防止占用内存
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void clearFiles() {
        log.info("定时任务...");
    }

}
