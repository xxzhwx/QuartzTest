package com.xxzhwx;

import org.quartz.*;

import java.util.Date;

public class JobB implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail job = context.getJobDetail();
        JobDataMap dataMap = job.getJobDataMap();
        String userName = (String) dataMap.get("userName");
        System.out.println("Hello, " + userName + ". It is " + new Date() + " now.");
    }
}
