package activiti;

import org.activiti.engine.ProcessEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T10_PersonalTask {

    @Autowired
    private ProcessEngine processEngine;

    @Test
    public void deploy() {

    }


    //    个人任务分配的几种方式
    //    1.分配到具体人【略】
    //    2.使用流程变量【可以在开启流程的时候设置变量值】#{},${}--->这两种形式一样
    //    3.
    @Test
    public void method2() {


    }

}
