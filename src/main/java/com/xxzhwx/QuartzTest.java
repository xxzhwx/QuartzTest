package com.xxzhwx;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class QuartzTest {
    public static void main(String[] args) {
        run();
        //runQuartz();
    }

    /**
     * 测试通过property文件和xml文件初始化jobs
     */
    public static void run() {
        try {
            Scheduler scheduler = new StdSchedulerFactory("quartz.properties").getScheduler(); // StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            System.out.println("Scheduler: " + scheduler.getSchedulerName() + " started at " + new Date());

            //Thread.sleep(100000);
            //scheduler.shutdown(true); // Wait the executing job to finish, and then shutdown the scheduler.
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试直接用代码添加和管理jobs
     */
    public static void runQuartz() {
        try {
            JobDetail jobDetail = JobBuilder.newJob()
                    .withIdentity("myJobName", "myJobGroupName")
                    .ofType(JobA.class)
                    .requestRecovery(false)
                    .storeDurably(false)
                    .build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("myTriggerName", "myTriggerGroupName")
                    //.forJob(jobDetail)
                    .startAt(new Date())
                    //.modifiedByCalendar()
                    .withSchedule(SimpleScheduleBuilder
                            .simpleSchedule()
                            .withIntervalInSeconds(1)
                            .withRepeatCount(10 - 1))
                    .build();

            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();

            Thread.sleep(10000);

            System.err.println("Shutting down ...");
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
