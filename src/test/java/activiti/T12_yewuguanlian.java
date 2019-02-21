package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Comment;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T12_yewuguanlian {

    /**
     * 线程局部变量测试
     */
    public static void main(String[] args) {
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("设置线程变量");
                threadLocal.set("1111");
                System.out.println(threadLocal.get());

            }

        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Object o = threadLocal.get();
                System.out.println("线程内容：" + o);
            }
        }).start();


    }


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
            System.out.println(
                    "批注时间：" + comment.getTime() +
                            "批注内容：" + comment.getFullMessage() +
                            "批注人：" + comment.getUserId()
            );

        }
    }

    /**
     * 查询自己的名下的任务进行办理
     */
    @Test
    public void doTask() {

        //根据BusinseeKey查询到task    自己完成任务[提交任务]   下一个任务的人由监听器指定
        String userName = "root2";//用户从session中获取
        String businessKey = "1";//从前台传入
        HashMap<String, Object> map = new HashMap<>();
        String isPass = "提交";//从前台传入
        map.put("key", isPass);
        //根据用户名获取任务
        TaskService taskService = processEngine.getTaskService();
        //添加批注信息--放在了当前线程中，查看源码可知
        Authentication.setAuthenticatedUserId(userName);
        //设置批注人
        List<Task> list = taskService.createTaskQuery()
                .taskAssignee(userName)
                .processInstanceBusinessKey(businessKey)
                .list();
        Task task = null;
        if (null != list && list.size() > 0) {
            task = list.get(0);
        }
        //    判断流程是否结束----
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(task.getProcessInstanceId())
                .singleResult();
        //taskService.addComment("任务id", "流程实例id", "批注信息");
        String comment = "批注信息";
        taskService.addComment(task.getId(), processInstance.getId(), comment);
        taskService.complete(task.getId(), map);

        // 启动成功后更改业务表状态   根据 businessKey 更改
        if (null != processInstance) {
            //查询输出连线判断是否结束
            //从流程实例中获取活动节点---根据活动节点去流程定义中查询输出
            if (isPass.equals("提交")) {
                System.out.println("流程未结束，状态未进行中");
            } else {
                System.out.println("流程结束，修改业务表记录状态");
            }
        } else {
            System.out.println("流程结束，修改状态");
        }
    }

    /**
     * 查询代办任务流程图
     * 根据任务ID得到流程实例
     * 根据流程实例中的部署ID得到流程定义
     * 根据流程定义id得到图片输出流
     * 查询活动节点得到坐标  在页面上标注
     * eg:<div style="postion: absoulte;left: 175px;width: 10px"
     */
    @Test
    public void getProcessPic() {
        String taskId = "197505";
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        //    节点坐标的获取：获取到流程实例-得到节点坐标-通过定义实体来获取相关坐标
        String processInstanceId = task.getProcessInstanceId();
        Execution execution = processEngine.getRuntimeService().createExecutionQuery().processInstanceId(processInstanceId).singleResult();
        //活动节点
        String activityId = execution.getActivityId();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery()
                .processInstanceId(processInstanceId)
                .singleResult();
        String processDefinitionId = processInstance.getProcessDefinitionId();
        //通过流程定义id获取流程定义实体
        ProcessDefinitionEntity processDefinitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);
        ActivityImpl activity = processDefinitionEntity.findActivity(activityId);
        int height = activity.getHeight();
        int width = activity.getWidth();
        int x = activity.getX();
        int y = activity.getY();

        System.out.println(
                "长：" + height +
                        "宽：" + width +
                        "x：" + x +
                        "y：" + y
        );

    }
}
