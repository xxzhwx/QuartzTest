package com.xxzhwx;

import org.quartz.*;

import java.util.Date;

public class JobA implements Job {
    private static int i = 0;
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            JobDetail job = jobExecutionContext.getJobDetail();
            JobKey key = job.getKey();
            System.out.println("Job name:" + key.getName()
                    + ",Job group:" + key.getGroup()
                    + ",now:" + new Date()
                    + ",i:" + i);

            i++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
