package com.bwc.pg.batch.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Component
public class BatchJobLauncher extends QuartzJobBean {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job paymentJob;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        try {
            JobParameters params = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(paymentJob, params);
        } catch (Exception e) {
            throw new JobExecutionException("Job execution failed", e);
        }
    }
}