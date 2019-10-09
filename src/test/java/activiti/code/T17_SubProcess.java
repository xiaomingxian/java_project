package activiti.code;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T17_SubProcess {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @Test
    public void deploy() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/huiqian/SubProcess.bpmn")
                .addClasspathResource("bpmn/huiqian/SubProcess.png")
                .deploy();

        System.out.println("------->部署实例：" + deploy.getId());
    }

    @Test
    public void start() {
        String key = "SubProcess";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);

        System.out.println("--->开启成功" + processInstance.getId());

    }

    @Test
    public void doTask() {
        //查询任务并办理
        String user = "jerry";
        Task task = taskService.createTaskQuery().taskAssignee(user).singleResult();

        String executionId = task.getExecutionId();
        System.out.println("=========>执行实例id:" + executionId);


        taskService.complete(task.getId());

        System.out.println(task.getAssignee() + "  " + task.getName());

    }


}
