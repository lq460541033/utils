package com.bpf.springbootmail.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 多线程执行定时任务
 * @author lq
 * 20190922
 */
//所有的定时任务都放在一个线程池中，定时任务启动时使用不同都线程。
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //设定一个长度10的定时任务线程池
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
