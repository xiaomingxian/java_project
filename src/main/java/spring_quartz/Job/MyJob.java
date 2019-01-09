package spring_quartz.Job;

import org.quartz.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyJob implements Job {

    private String string1;
    private String string2;

    private Double adouble;
    private Float afloat;


    public Double getAdouble() {
        return adouble;
    }

    public void setAdouble(Double adouble) {
        this.adouble = adouble;
    }

    public Float getAfloat() {
        return afloat;
    }

    public void setAfloat(Float afloat) {
        this.afloat = afloat;
    }

    public String getString1() {
        return string1;
    }

    public void setString1(String string1) {
        this.string1 = string1;
    }

    public String getString2() {
        return string2;
    }

    public void setString2(String string2) {
        this.string2 = string2;
    }

    //Job只有这一个方法
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Date d = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        String format = sd.format(d);
        //常规参数---name--group--class...
        //jobExecutionContext.getJobDetail().getKey().getClass();
        //JobKey key = jobExecutionContext.getJobDetail().getKey();
        //System.out.println("MyJob  当前时间:" + format + "   获取参数[name:" + key.getName() + ",group:" + key.getGroup() + ",class:" + key.getClass() + "]");
        //TriggerKey key1 = jobExecutionContext.getTrigger().getKey();
        //System.out.println("Triggle  当前时间:" + format + "   获取参数[name:" + key1.getName() + ",group:" + key1.getGroup() + ",class:" + key1.getClass() + "]");


        //获取自定义参数---存储在jobExecutionContext-中的-DataMap
        //方式一
        //JobDataMap jdm = jobExecutionContext.getMergedJobDataMap();
        //System.out.println("自定义 job参数:"+jdm.get("string1"));
        //System.out.println("自定义 triggle参数:"+jdm.get("string2"));

        //方式二
        //JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        //JobDataMap triggleDataMap = jobExecutionContext.getTrigger().getJobDataMap();
        //System.out.println("job参数:"+jobDataMap.getString("string1"));
        //System.out.println("triggle参数:"+triggleDataMap.getString("string2"));


        //    方式三：在Job实现类中定义同名的参数
        //System.out.println(string1);
        //System.out.println(string2);
        //System.out.println(adouble);
        //System.out.println(afloat);

        //获取开始和结束时间
        //System.out.println(jobExecutionContext.getTrigger().getStartTime());
        //System.out.println(jobExecutionContext.getTrigger().getPreviousFireTime());
        //System.out.println(jobExecutionContext.getTrigger().getEndTime());

        //try {
        //    Thread.sleep(5000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}

        System.out.println("hello ...");

    }
}
