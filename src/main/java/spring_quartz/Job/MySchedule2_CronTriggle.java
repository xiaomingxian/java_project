package spring_quartz.Job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class MySchedule2_CronTriggle {
    public static void main(String[] args) throws SchedulerException, InterruptedException {


        //    1.创建Job
        JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("myJob2", "group2").build();
        //2.创建Triggle
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("myTriggle2", "group2").withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ?")).build();

        //3.schedule
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //
        scheduler.start();
        //返回的是最近一次要执行的时间
        Date date = scheduler.scheduleJob(job, trigger);
        System.out.println(date);
        //执行两秒自动挂起---从0秒开始执行   ("* * * * * ?")
        Thread.sleep(2000);
        scheduler.standby();
        //3s后重新开始-----会吧挂起之后未执行的任务一并执行
        //Thread.sleep(3000);
        scheduler.start();
        //关闭
        Thread.sleep(3000);
        //true:在所有任务执行完毕才会关闭---false:立即关闭-默认false
        //scheduler.shutdown(true);
        scheduler.shutdown(false);
        System.out.println(scheduler.isShutdown());
        //scheduler.start();//The Scheduler cannot be restarted after shutdown() has been called.

    }
}
