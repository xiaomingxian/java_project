package activiti;

import org.activiti.engine.ProcessEngine;
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

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T6_LineTest {


    @Autowired
    private ProcessEngine processEngine;

    /**
     * 发布流程
     */
    @Test
    public void deploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/ParallelGateWay.bpmn")
                .addClasspathResource("bpmn/ParallelGateWay.png")
                .deploy();
        System.out.println("--->流程id:" + deploy.getId());
    }

    /**
     * 启动流程
     */
    @Test
    public void start() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "ParallelGateWay";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        System.out.println("流程实例Id:" + processInstance.getId());

    }

    /**
     * 多连线有条件情况下
     * 必须传入参数 否则会报错
     * 本测试流程已完成
     */
    @Test
    public void doTask() {
        TaskService taskService = processEngine.getTaskService();

        //指定参数
        HashMap<String, Object> map = new HashMap<>();
        map.put("count", "重要");

        Task task = taskService.createTaskQuery()
                .taskAssignee("张三").singleResult();
        taskService.complete(task.getId(), map);
        //taskService.complete(task.getId());
    }

}
