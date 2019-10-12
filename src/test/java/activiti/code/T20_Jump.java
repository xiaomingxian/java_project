package activiti.code;


import activiti.util.JDJumpTaskCmd;
import activiti.util.ParallelJumpTaskCmd;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.TaskServiceImpl;
import org.activiti.engine.impl.interceptor.CommandExecutor;
import org.activiti.engine.impl.pvm.ReadOnlyProcessDefinition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T20_Jump {

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


        //流程定义实体
        ReadOnlyProcessDefinition processDefinitionEntity = (ReadOnlyProcessDefinition) repositoryService.getProcessDefinition("myProcess_1:1:4");


        // 目标节点
        ActivityImpl destinationActivity = (ActivityImpl) processDefinitionEntity.findActivity("UserTask1");
        // 当前节点
        ActivityImpl currentActivity = (ActivityImpl) processDefinitionEntity.findActivity("UserTask2");


        commandExecutor.execute(new ParallelJumpTaskCmd(executionId, destinationActivity, new HashMap<>(), currentActivity));


    }
}
