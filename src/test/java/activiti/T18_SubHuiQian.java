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
public class T18_SubHuiQian {


    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;


    @Test
    public void deploy() {
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/huiqian/SubHuiQian.bpmn")
                .addClasspathResource("bpmn/huiqian/SubHuiQian.png")
                .deploy();

        System.out.println("------->部署实例：" + deploy.getId());
    }

    @Test
    public void start() {
        String key = "SubHuiQian";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);

        System.out.println("--->开启成功" + processInstance.getId());

    }

    /**
     * 多流程子实例(n条)与 会签节点(m条)
     */
    @Test
    public void doTask() {
        //查询任务并办理
        String user = "tom";
        Task task = taskService.createTaskQuery().taskAssignee(user).singleResult();

        String executionId = task.getExecutionId();
        System.out.println("=========>执行实例id:" + executionId);

        ArrayList<String> list = new ArrayList<>();
        list.add("sub1");
        list.add("sub2");
        list.add("sub3");
        list.add("sub4");

        ArrayList<String> users = new ArrayList<>();
        users.add("张三");
        users.add("李四");
        users.add("王五");
        users.add("赵六");


        Map map = new HashMap();
        map.put("subprocess", list);
        map.put("users", users);

        taskService.complete(task.getId(), map);

        System.out.println(task.getAssignee() + "  " + task.getName());

    }

    /**
     * 办理会签与子流程任务
     */
    @Test
    public void doSubTask() {

        String name = "张三";
        List<Task> list = taskService.createTaskQuery().taskAssignee(name).list();

        list.forEach(k -> System.out.println("----->" + k));

        Task task = list.get(0);
        HashMap map = new HashMap();
        map.put("okCount", 4);
        map.put("subCount", 4);
        taskService.complete(task.getId(), map);

    }


}
