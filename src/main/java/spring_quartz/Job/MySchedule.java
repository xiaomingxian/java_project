package spring_quartz.Job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class MySchedule {
    public static void main(String[] args) throws SchedulerException {
        //1.创建jobDetail示例，并与MyJob绑定--设定唯一标识---builder模式创建(反射)
        //传参测试
        //JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob", "group1").usingJobData("string1", "hello Value").usingJobData("afloat", 10.5F).build();
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("myJob", "group1").build();
        //2.创建Triggle-----group与上面不是同一个组(不是同一个类)
        //SimpleTrigger triggle = TriggerBuilder.newTrigger().withIdentity("myTrigle", "group1").startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).usingJobData("string2", "hello triggle").usingJobData("adouble", 20D).build();
        //设置开始和结束时间
        Date date = new Date();
        Date date1 = new Date();
        //simpleTriggle
        SimpleTrigger triggle = TriggerBuilder.newTrigger().withIdentity("myTrigle", "group1").startAt(new Date(date.getTime() + 3000)).endAt(new Date(date1.getTime() + 6000)).withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever()).build();
        //3.创建Schedule----factory模式
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        scheduler.scheduleJob(jobDetail, triggle);


        //获取JobDetail相关属性
        //System.out.println("name:" + jobDetail.getKey().getName());
        //System.out.println("group:" + jobDetail.getKey().getGroup());//默认是default--如果没有设置的话
        //System.out.println("calss:" + jobDetail.getKey().getClass());


    }
}
