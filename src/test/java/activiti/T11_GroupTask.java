package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * 组任务
 * 办理人三种制定方式:写死,流程变量,监听器
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T11_GroupTask {

    @Autowired
    private ProcessEngine processEngine;


    @Test
    public void deploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/Group3.bpmn")
                .addClasspathResource("bpmn/Group3.png")
                .deploy();

        System.out.println("---->流程部署成功：" + deploy.getId());

    }

    /**
     * GroupTask.bpmn
     * 任务开启：分配方式写死数据
     */
    @Test
    public void start() {

        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "GroupTask";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        System.out.println("--->流程开启成功：" + processInstance.getId());


    }


    /**
     * GroupTask2.bpmn
     * 分配方式2：使用流程变量【与第一种方式相同，只是将数据做成动态】
     */
    @Test
    public void start2() {

        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "Group2";
        HashMap<String, Object> map = new HashMap<>();
        map.put("users", "Tom,Jerry,Carry");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        System.out.println("--->流程开启成功：" + processInstance.getId());


    }

    /**
     * GroupTask.bpmn
     * 任务开启：使用监听器分配成员
     */
    @Test
    public void start3() {

        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "Group3";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        System.out.println("--->流程开启成功：" + processInstance.getId());


    }

    /**
     * 查询任务：identity中组任务每个成员有两条数据【既是参与者又是候选者】【对应taskId与Proc_Inst_Id】
     * 任务如果拾取成功的话，就成了个人任务，用组任务就查询不到
     */
    @Test
    public void queryTask() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery()
                .taskCandidateUser("经理")
                //.taskCandidateOrAssigned("经理")
                .list();

        System.out.println("=====>" + tasks);

    }

    /**
     * 查询成员列表
     * 如果被非成员拾取，查询记录会多出一条【因为个人任务与组任务都在 act_ru_identitylink 表中有记录】
     */
    @Test
    public void queryIdentityLinks() {
        TaskService taskService = processEngine.getTaskService();
        String taskkId = "162504";
        List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(taskkId);
        System.out.println("---->参与成员：");
        for (IdentityLink identityLink : identityLinksForTask) {
            System.out.println(identityLink.getUserId());
        }
    }

    /**
     * 添加办理成员
     */
    @Test
    public void addUser() {
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .taskCandidateUser("经理")
                .singleResult();
        String taskId = task.getId();
        String newUser = "老总";
        taskService.addCandidateUser(taskId, newUser);
        System.out.println("------>添加决策组成员成功");


    }

    /**
     * 删除决策组成员
     */
    @Test
    public void deleteUser() {
        TaskService taskService = processEngine.getTaskService();
        String user = "经理";
        Task task = taskService.createTaskQuery()
                .taskCandidateUser(user)
                .singleResult();

        String taskId = task.getId();
        String deleteUserName = "老总";
        taskService.deleteCandidateUser(taskId, deleteUserName);
        System.out.println("---->删除决策组成员成功");

    }

    /**
     * 拾取任务【不是组任务不能回退】
     * identi..表中只删除了相关的候选人信息，参与者信息还在【为了证明他参与过】
     */
    @Test
    public void claim() {
        TaskService taskService = processEngine.getTaskService();
        String taskId = "162504";
        //String assignee = "经理";//assign可以任意指定，不必是候选组里的成员
        String assignee = "自定义用户";//assign可以任意指定，不必是候选组里的成员
        taskService.claim(taskId, assignee);

        List<Task> tasks = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .list();

        System.out.println("--->任务拾取成功：" + tasks);

    }

    /**
     * 回退任务
     * identi..表中只删除了相关的候选人信息，参与者信息还在【为了证明他参与过】
     */
    @Test
    public void unclaim() {
        TaskService taskService = processEngine.getTaskService();
        String taskId = "162504";
        //taskService.setAssignee(taskId, null);
        //与以上步骤原理相同-->查看原码
        taskService.unclaim(taskId);
        System.out.println("---->回退回组任务成功.");
    }


}
