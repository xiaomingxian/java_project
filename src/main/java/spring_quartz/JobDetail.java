package spring_quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class JobDetail extends QuartzJobBean {


    private AnotherBean anotherBean;

    public void setAnotherBean(AnotherBean anotherBean) {
        this.anotherBean = anotherBean;
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {


        System.out.println("方式二...");
        anotherBean.printMsg();
    }
}
