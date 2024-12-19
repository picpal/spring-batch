package com.bwc.pg.batch.config;

import com.bwc.pg.batch.job.BatchJobLauncher;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail quartzJobDetail() {
        return JobBuilder.newJob(BatchJobLauncher.class)
                .withIdentity("paymentQuartzJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger quartzTrigger() {
        return TriggerBuilder.newTrigger()
                .forJob(quartzJobDetail())
                .withIdentity("paymentQuartzTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?"))
                .build();
    }
}