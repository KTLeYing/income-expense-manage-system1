package com.mzl.incomeexpensemanagesystem1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.Executor;

/**
 * @ClassName :   ThreadPoolConfig
 * @Description: 线程池配置
 * @Author: mzl
 * @CreateDate: 2022/3/17 9:42
 * @Version: 1.0
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    //TODO:java config的方式显示注入Bean
    /**
     * 任务-线程池-调度配置(发送邮件)
     * @return
     */
    @Bean("sendEmailTaskExecutor")
    public Executor sendEmailTaskExecutor(){
        ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
        //核心线程数
        executor.setCorePoolSize(300);
        //最大核心线程数
        executor.setMaxPoolSize(5000);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-service-");
        //设定队列等待的被调度的任务的数量
        executor.setQueueCapacity(100);
        //配置超时时间,默认60s
        executor.setKeepAliveSeconds(60);
        //初始化启动
        executor.initialize();
        return executor;
    }

    /**
     * 实现定时任务的并发和动态控制
     * 解决使用@Scheduled创建任务时无法在同一时间执行多个任务的
     * 如果没有指定TaskScheduler则会创建一个单线程的默认调度器
     * 参考教程：https://www.cnblogs.com/liangblog/p/14074747.html
     **/
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        //我这里设置的线程数是4,可以根据需求调整
        taskScheduler.setPoolSize(4);
        return taskScheduler;
    }

}
