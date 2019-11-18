package activiti.code;


import activiti.util.JDJumpTaskCmd;
import activiti.util.MulJumpTaskCmd;
import activiti.util.ParallelJumpTaskCmd;
import org.activiti.engine.*;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T20_Jump {

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ManagementService managementService;
    @Autowired
    private TaskServiceImpl taskServiceImpl;
    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;


    @Test
    public void deploy() {
        repositoryService.createDeployment().addClasspathResource("bpmn/jump/Jump.bpmn").deploy();
    }


    @Test
    public void start() {
        String key = "daling";
        String busKey = "1";
        HashMap<String, Object> map = new HashMap<>();
        String[] users = {"tom", "jerry", "alice"};
        map.put("users", Arrays.asList(users));
        //runtimeService.startProcessInstanceByKey(key, busKey, map);
        runtimeService.startProcessInstanceByKey(key, busKey);
    }

    @Test
    public void isMul() {

        String assignee = "c";

        Task task = taskService.createTaskQuery()
                //.taskAssignee(assignee)
                .singleResult();
        //String name = task.getName();

        String taskDefinitionKey = task.getTaskDefinitionKey();

        String executionId = task.getExecutionId();
        Execution execution = runtimeService.createExecutionQuery().executionId(executionId).singleResult();
        String processInstanceId = execution.getProcessInstanceId();

        System.out.println(processInstanceId.equals(executionId));

        //发布
        HashMap<String, Object> map = new HashMap<>();
        //String[] users = {"张三", "李四", "王五", "赵六"};
        //map.put("users", Arrays.asList(users));


        CommandExecutor commandExecutor = taskServiceImpl.getCommandExecutor();


        String processDefinitionId = task.getProcessDefinitionId();
        //流程定义实体
        ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService.getProcessDefinition(processDefinitionId);


        // 目标节点
        ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity.findActivity("usertask4");//参数是节点id
        // 当前节点
        ActivityImpl currentActivity = (ActivityImpl) processDefinitionEntity.findActivity(taskDefinitionKey);


        activiti.util.csdn.JDJumpTaskCmd jumpTaskCmd = new activiti.util.csdn.JDJumpTaskCmd(task.getId(), executionId, processInstanceId, destinationActivity, map, currentActivity, "删除原因");

        commandExecutor.execute(jumpTaskCmd);
    }



    @Test
    public void stop() {
        String proId = "10001";
        Authentication.setAuthenticatedUserId("stop");
        runtimeService.deleteProcessInstance(proId, "人工终止");
    }


    /**
     * method1
     */
    @Test
    public void jump() {
        try {
            Map<String, Object> vars = new HashMap<String, Object>();
            String[] v = {"shareniu1", "shareniu2", "shareniu3", "shareniu4"};
            vars.put("assigneeList", Arrays.asList(v));
            ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService.getProcessDefinition("daling:1:4");
            // 目标节点
            ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity.findActivity("usertask5");
            String executionId = "2501";
            // 当前节点
            ActivityImpl currentActivity = (ActivityImpl) processDefinitionEntity.findActivity("usertask3");


            managementService.executeCommand(new JDJumpTaskCmd(executionId, destinationActivity, vars, currentActivity));
            //内部调用的是自定义类的excute方法
            //    public <T> T execute(Command<T> command) {
            //        return this.execute(this.defaultConfig, command);
            //    }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * method2
     */
    @Test
    public void jump2() {
        Map<String, Object> vars = new HashMap<String, Object>();
        String[] v = {"shareniu1", "shareniu2", "shareniu3", "shareniu4"};
        vars.put("assigneeList", Arrays.asList(v));
        //分享牛原创(尊重原创 转载对的时候第一行请注明，转载出处来自分享牛http://blog.csdn.net/qq_30739519)
        CommandExecutor commandExecutor = taskServiceImpl.getCommandExecutor();

        //流程定义实体
        ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService.getProcessDefinition("daling:1:4");

        String executionId = "2501";
        // 目标节点
        ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity.findActivity("usertask2");
        // 当前节点
        ActivityImpl currentActivity = (ActivityImpl) processDefinitionEntity.findActivity("usertask5");


        commandExecutor.execute(new JDJumpTaskCmd(executionId, destinationActivity, vars, currentActivity));
    }

    /**
     * 分支节点跳转
     */
    @Test
    public void fenZhiJump() {
        /**
         runtimeService.startProcessInstanceByKey("myProcess_1");
         Task task = taskService.createTaskQuery().taskAssignee("张三").singleResult();
         taskService.complete(task.getId());
         */
        CommandExecutor commandExecutor = taskServiceImpl.getCommandExecutor();

        String executionId = "2507";
        String parentId = "2501";


        //流程定义实体
        ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService.getProcessDefinition("myProcess_1:1:4");


        // 目标节点
        ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity.findActivity("_4");//参数是节点id
        // 当前节点
        ActivityImpl currentActivity = (ActivityImpl) processDefinitionEntity.findActivity("_5");


        commandExecutor.execute(new ParallelJumpTaskCmd(executionId, parentId, destinationActivity, new HashMap<>(), currentActivity));
    }

    /**
     * 多实例节点跳转
     */
    @Test
    public void mulJump() {
        //发布
        //repositoryService.createDeployment().addClasspathResource("bpmn/jump/multiInstance.bpmn").deploy();

        HashMap<String, Object> map = new HashMap<>();
        String[] users = {"张三", "李四", "王五", "赵六"};
        map.put("users", Arrays.asList(users));
        //runtimeService.startProcessInstanceByKey("mul",map);


        CommandExecutor commandExecutor = taskServiceImpl.getCommandExecutor();

        String executionId = "2501";
        String parentId = "2501";


        //流程定义实体
        ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService.getProcessDefinition("mul:1:4");


        // 目标节点
        ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity.findActivity("task1");//参数是节点id
        // 当前节点
        ActivityImpl currentActivity = (ActivityImpl) processDefinitionEntity.findActivity("task2");


        //commandExecutor.execute(new MulJumpTaskCmd(executionId, parentId, destinationActivity, new HashMap<>(), currentActivity));
        commandExecutor.execute(new MulJumpTaskCmd(executionId, parentId, destinationActivity, map, currentActivity));


    }
}
