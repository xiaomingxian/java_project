package activiti;

import org.activiti.engine.IdentityService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.InputStream;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class MyTest {

    ProcessEngine processEngine = null;

    @Before
    public void before() {
        processEngine = ProcessEngines.getDefaultProcessEngine();


    }

    //部署流程定义，启动流程实例
    @Test
    public void testTask() throws Exception {
        // 1 发布流程
        InputStream inputStreamBpmn = this.getClass().getResourceAsStream("bpmn/task_public.bpmn");
        InputStream inputStreamPng = this.getClass().getResourceAsStream("bpmn/task_public.png");
        processEngine.getRepositoryService()//
                .createDeployment()//
                .addInputStream("bpmn/task_public.bpmn", inputStreamBpmn)//
                .addInputStream("bpmn/task_public.png", inputStreamPng)//
                .deploy();

        /**在部署流程定义和启动流程实例的中间，设置组任务的办理人，向Activity表中存放组和用户的信息*/
        IdentityService identityService = processEngine.getIdentityService();//认证：保存组和用户信息
        identityService.saveGroup(new GroupEntity("部门经理"));//建立组
        identityService.saveGroup(new GroupEntity("总经理"));//建立组
        identityService.saveUser(new UserEntity("小张"));
        identityService.saveUser(new UserEntity("小李"));
        identityService.saveUser(new UserEntity("小王"));
        identityService.createMembership("小张", "部门经理");//建立组和用户关系
        identityService.createMembership("小李", "部门经理");//建立组和用户关系
        identityService.createMembership("小王", "总经理");//建立组和用户关系


        // 2 启动流程
        ProcessInstance pi = processEngine.getRuntimeService()//
                .startProcessInstanceByKey("task_public:1:4");
        System.out.println("pid:" + pi.getId());
    }

    //查询我的个人任务列表
    @Test
    public void findMyTaskList() {
        String userId = "唐僧";
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskAssignee(userId)//指定个人任务查询
                .list();
        for (Task task : list) {
            System.out.println("id=" + task.getId());
            System.out.println("name=" + task.getName());
            System.out.println("assinee=" + task.getAssignee());
            System.out.println("assinee=" + task.getCreateTime());
            System.out.println("executionId=" + task.getExecutionId());

        }
    }

    //查询组任务列表
    @Test
    public void findGroupList() {
        String userId = "小李";//小张，小李可以查询结果，小王不可以，因为他不是部门经理
        List<Task> list = processEngine.getTaskService()//
                .createTaskQuery()//
                .taskCandidateUser(userId)//指定组任务查询
                .list();
        for (Task task : list) {
            System.out.println("id=" + task.getId());
            System.out.println("name=" + task.getName());
            System.out.println("assinee=" + task.getAssignee());
            System.out.println("assinee=" + task.getCreateTime());
            System.out.println("executionId=" + task.getExecutionId());
            System.out.println("##################################");

        }
    }

    //查询组任务成员列表
    @Test
    public void findGroupUser() {
        String taskId = "4408";
        List<IdentityLink> list = processEngine.getTaskService()//
                .getIdentityLinksForTask(taskId);
        for (IdentityLink identityLink : list) {
            System.out.println("userId=" + identityLink.getUserId());
            System.out.println("taskId=" + identityLink.getTaskId());
            System.out.println("piId=" + identityLink.getProcessInstanceId());
            System.out.println("######################");
        }
    }

    //完成任务
    @Test
    public void completeTask() {
        String taskId = "5108";
        processEngine.getTaskService()//
                .complete(taskId);//
        System.out.println("完成任务");
    }
}

