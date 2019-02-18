package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T12_yewuguanlian {


    @Autowired
    private ProcessEngine processEngine;

    @Test
    public void start() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String businessKey = "3";
        //key的设置规范化--一般类名【业务表的实体类(也可自定义)】就是他的key名
        //String key = 类名.class.getSimpleName();
        String key = "LeaveBill";
        Map<String, Object> map = new HashMap<>();
        map.put("user", "root2");
        //map.put("key", "提交");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, businessKey, map);
        System.out.println("流程启动成功");
    }

    /**
     * 根据用户名查询自己的任务【未提交/为办理】
     * 根据Taskid-->Bussinesskey查询业务表信息
     * 根据Taskid查询连线信息
     */
    @Test
    public void queryTaskMsg() {
        TaskService taskService = processEngine.getTaskService();
        //根据assign查询任务信息
        String taskId = "197505";
        Task task = taskService.createTaskQuery()
                .taskId(taskId).singleResult();
        //[单流程]非并行网关下excutionId与ProcessId相同
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(task.getExecutionId())//这里：task.getProcessInstanceId()==task.getExecutionId()
                .singleResult();
        System.out.println("-->" + processInstance.getBusinessKey());
        //    --------------------查询连线信息
        //    从xml中查询【流程定义】
        //    根据taskId获取到流程实例根据流程实例获取到活动Id
        //使用流程定义实体获取到活动id相关信息
        String activityId = processInstance.getActivityId();
        System.out.println("--------->活动Id:" + activityId);
        RepositoryService repositoryService = processEngine.getRepositoryService();

        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        ActivityImpl activity = processDefinitionEntity.findActivity(activityId);
        List<PvmTransition> outgoingTransitions = activity.getOutgoingTransitions();

        for (PvmTransition pvmTransition : outgoingTransitions) {
            System.out.println("outcome_name:" + pvmTransition.getProperty("name"));
        }


    }

    /**
     * 根据taskId和流程实例查询批注信息
     */
    @Test
    public void getComments() {
        TaskService taskService = processEngine.getTaskService();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        String taskId = "197505";
        String processInstanceId = null;
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        processInstanceId = task.getProcessInstanceId();

        List<Comment> processInstanceComments = taskService.getProcessInstanceComments(taskId, processInstanceId);
        System.out.println("批注数量：" + processInstanceComments.size());
        for (Comment comment : processInstanceComments) {
            System.out.println(comment);

        }


    }

    /**
     * 查询自己的名下的任务进行办理
     */
    @Test
    public void myPass() {

        //根据BusinseeKey查询到task    自己完成任务[提交任务]   下一个任务的人由监听器指定
        String userName = "root2";//用户从session中获取
        String businessKey = "1";//从前台传入
        HashMap<String, Object> map = new HashMap<>();
        String isPass = "提交";
        map.put("key", isPass);
        //根据用户名获取任务
        TaskService taskService = processEngine.getTaskService();
        //添加批注信息
        //设置批注人
        taskService.addComment("任务id", "流程实例id", "批注信息");
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(userName)
                .processInstanceBusinessKey(businessKey)
                .list();
        Task task = null;
        if (null != list && list.size() > 0) {
            task = list.get(0);
        }
        taskService.complete(task.getId(), map);
        // 启动成功后更改业务表状态   根据 businessKey 更改

    }
    /**
     * 办理任务
     * 根据当前用户查询到任务 ---》再根据bussinessKey查询到业务信息 ---》同意与否与流程连线上的变量有关【可以查询到】
     * ---》批注信息的查询
     */

}
