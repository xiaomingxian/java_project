package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T13_Deploy_moreThanOne {


    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private RepositoryService repositoryService;


    @Test
    public void deploy1() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/twice_test/T1.bpmn")
                .addClasspathResource("bpmn/twice_test/T1.png")
                .deploy();

        System.out.println("----->发布成功:" + deploy);

    }

    @Test
    public void deploy2() {
        //与第一次发布的id相同
        InputStream resourceAsStream = this.getClass().getResourceAsStream("/bpmn/twice_test/T2.zip");
        ZipInputStream zipIn = new ZipInputStream(resourceAsStream);
        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipIn)
                .deploy();

        System.out.println("----->发布成功:" + deploy);

    }

    @Test
    public void start() {
        String key = "T";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        System.out.println("------>流程实例：" + processInstance);

    }


}
