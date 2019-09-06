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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T16_CountHuiQian {
    /**
     * 根据数量进行判断
     */


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
                .addClasspathResource("bpmn/huiqian/CountHuiQian.bpmn")
                .addClasspathResource("bpmn/huiqian/CountHuiQian.png")
                .deploy();

        System.out.println("------->部署实例：" + deploy.getId());
    }


    /**
     * 开启流程实例--设置会签人
     */
    @Test
    public void start() {

        String instanceKey = "CountHuiQian";


        //设置会签人员
        Map vars = new HashMap<>();
        List<String> sigList = new ArrayList<>();
        sigList.add("张三");
        sigList.add("李四");
        sigList.add("王五");
        sigList.add("赵六");
        sigList.add("田七");

        vars.put("users", sigList);

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
        String user = "赵六";
        Task task = taskService.createTaskQuery().taskAssignee(user).singleResult();

        String executionId = task.getExecutionId();
        System.out.println("=========>执行实例id:" + executionId);
        Integer count = (Integer) runtimeService.getVariables(executionId).get("count");
        if (count != null) count++;
        else count = 0;

        Map map = new HashMap();
        map.put("count", count);

        taskService.complete(task.getId(), map);

        System.out.println(task.getAssignee() + "  " + task.getName());

    }
}
