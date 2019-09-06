package activiti;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 并行会签
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T15_HuiQianB {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    /**
     * 部署
     */
    @Test
    public void deploy() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/huiqian/BXHuiQian.bpmn")
                .addClasspathResource("bpmn/huiqian/BXHuiQian.png")
                .deploy();

        System.out.println("------->部署实例：" + deploy.getId());
    }


    /**
     * 开启流程实例--设置会签人
     */
    @Test
    public void start() {

        String instanceKey = "BXHuiQian";


        //设置会签人员
        Map vars = new HashMap<>();
        List<String> sigList = new ArrayList<>();
        sigList.add("张三");
        sigList.add("李四");
        sigList.add("王五");
        sigList.add("赵六");
        sigList.add("田七");

        vars.put("users", sigList);
        vars.put("pass", "ok");
        vars.put("ok", "yes");

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(instanceKey, vars);

        System.out.println("======>" + processInstance.getProcessDefinitionId());

    }

    /**
     * 处理会签任务
     * 并行--当condition又一个满足条件时 此节点就结束
     */
    @Test
    public void doTask() {
        //查询任务并办理
        String user = "jerry";
        Task task = taskService.createTaskQuery().taskAssignee(user).singleResult();

        Map map = new HashMap();
        map.put("pass", "ok");

        //taskService.complete(task.getId(), map);
        taskService.complete(task.getId());

        System.out.println(task.getAssignee() + "  " + task.getName());

    }

}
