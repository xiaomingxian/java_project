package activiti.code;


import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T19_FormTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Test
    public void deploy() {

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/huiqian/SubHuiQian.bpmn")
                .addClasspathResource("bpmn/huiqian/SubHuiQian.png")
                .deploy();


        //InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("bpmn/form/FormTest.bpmn");
        //Deployment deploy = repositoryService.createDeployment()
        //        .name("FormTest").addInputStream("FormTest",resourceAsStream).deploy();

        System.out.println("------->部署实例：" + deploy.getId());
    }

    /**
     * 根据流程定义id从开始时间获取信息
     * 根据任务id从任务节点获取信息
     */

}
