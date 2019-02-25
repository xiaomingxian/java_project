package activiti;


import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T3_ProcessInstance {
    @Autowired
    private ProcessEngine processEngine;

    //流程部署略

    /**
     * 启动流程的几种方式
     * 1.根据流程id/key【略】
     * 2.根据流程id/key和【带参数】
     * map参数【决定是驳回还是同意，还可以携带一些数据eg:请假时间，请假天数】
     * 3.根据流程id/key和bussiness_key_【关联业务id】
     * 4.根据流程id/key和bussiness_key_【关联业务id】和map参数
     * <p>
     * <p>
     * <p>
     * bussiness_key_在ru_excution【流程执行实例】表中
     */
    @Test
    public void startProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String processId = "";
        //method1略
        //method2
        Map map = null;
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processId, map);
        //    method3
        String bussiness_key = "";//业务表的id----每当流程启动的时候excution会增加一条执行实例【在此时绑定业务合理】
        runtimeService.startProcessInstanceById(processId, bussiness_key);
        //    method4
        runtimeService.startProcessInstanceById(processId, bussiness_key, map);

        //    开发中常用byKey的 method3/4
    }

    /**
     * 查询任务
     */
    @Test
    public void queryTask() {

        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                //查询条件有很多，根据业务进行选择
                //.processInstanceBusinessKey("bussiness_key")
                //.deploymentIdIn(new ArrayList<>())//ids
                .deploymentId("25001")//关联查询----task表关联excution表关联发布流程表
                //.executionId("excutionId")//运行实例id
                //.taskAssignee("tom")//根据办理人查询
                //.processVariableValueEquals("")//根据变量查询--有很多方法
                .list();

        for (Task task : list) {
            System.out.println(
                    "任务id:" + task.getId() +
                      " 运行实例id:" + task.getExecutionId() +
                            " 流程定义id:" + task.getTaskDefinitionKey() +
                            " 流程实例id:" + task.getTaskDefinitionKey() +
                            " 任务定义key:" + task.getTaskDefinitionKey() +
                            " 任务Name:" + task.getName() +
                            " 代办人:" + task.getAssignee() +
                            " time:" + task.getCreateTime() +
                            " 流程变量:" + task.getProcessVariables() +
                            " 任务变量:" + task.getTaskLocalVariables()
            );

        }


    }

    /**
     * 执行任务
     */
    @Test
    public void doTask() {
        TaskService taskService = processEngine.getTaskService();
        //method1--根据任务id完成
        taskService.complete("37502");
        //    method2--有分支的情况下传递参数指定方向【eg:可以放弃/驳回】
        //taskService.complete("任务id",new HashMap<>());
        //    method3---method2的基础上加boolean


    }

    /**
     * 判断是否审批完成
     * 当执行完成时excution表中无对应的数据【可以由此判断】
     * 任务id,流程id去task中查询
     */
    @Test
    public void isComplete() {
        //1.已知任务id
        Task task = processEngine.getTaskService().createTaskQuery()
                .taskId("17502").singleResult();
        //String executionId = task.getExecutionId();//这条查询不合理---一个流程可能有多个分支
        String processInstanceId = task.getProcessInstanceId();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance1 = runtimeService.createProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        if (null == processInstance1) {

            System.out.println("---->流程结束");
        } else {
            System.out.println("---->流程未结束");
        }

        // 2.   已知流程id
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("32501").singleResult();
        if (null == processInstance) {
            System.out.println("---->流程结束");
        } else {
            System.out.println("---->流程未结束");
        }
    }

    /**
     * 查询流程实例【已开始未结束的任务(正在执行)】
     */
    @Test
    public void queryProcessInstances() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery()
                .list();
        for (ProcessInstance processInstance : list) {
            System.out.println(processInstance);
        }
    }

    /**
     * 查询历史任务执行记录
     */
    @Test
    public void queryHistoryTask() {
        HistoryService historyService = processEngine.getHistoryService();
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
        List<HistoricTaskInstance> list = historicTaskInstanceQuery.list();
        int i = 0;
        for (HistoricTaskInstance historicTaskInstance : list) {
            System.out.println(historicTaskInstance);
            i++;
        }
        System.out.println("数量:" + i);
    }

    /**
     * 查询历史流程实例
     */
    @Test
    public void queryHistoryProcessInstances() {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                .list();
        for (HistoricProcessInstance historicProcessInstance : list) {
            System.out.println(
                    "流程实例Id:" + historicProcessInstance.getId() +
                            "流程部署Id:" + historicProcessInstance.getDeploymentId() +
                            "流程定义Id:" + historicProcessInstance.getProcessDefinitionId() +
                            "业务Id:" + historicProcessInstance.getBusinessKey() +
                            "流程实例Key:" + historicProcessInstance.getProcessDefinitionKey() +
                            "发起流程的用户Id:" + historicProcessInstance.getStartUserId() +
                            "流程开始时间:" + historicProcessInstance.getStartTime() +
                            "流程结束时间:" + historicProcessInstance.getEndTime() +
                            "流程持续时间【毫秒】:" + historicProcessInstance.getDurationInMillis()
            );

        }
    }


}
