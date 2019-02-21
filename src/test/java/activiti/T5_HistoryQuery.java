package activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T5_HistoryQuery {

    @Autowired
    private ProcessEngine processEngine;


    /**
     * act_hi_procinst
     * 查询历史流程实例
     * 查询套路：
     * 条件-->排序-->结果集
     */
    @Test
    public void queryHiProcessInstances() {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery()
                //.processInstanceId("111")//查询条件多种多样
                .list();

        for (HistoricProcessInstance historicTaskInstance : list) {
            System.out.println(
                    "历史流程实例Id:" + historicTaskInstance.getId() +
                            "业务Id:" + historicTaskInstance.getBusinessKey() +
                            "历史流程实例定义Id:" + historicTaskInstance.getProcessDefinitionKey() +
                            "结束时间为null表示未结束" + historicTaskInstance.getEndTime()
            );
        }
    }

    /**
     * act_hi_actinst
     * 查询历史活动信息
     */
    @Test
    public void queryHiActivi() {

        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .activityId("task_public")//查询条件多种多样
                .list();
        for (HistoricActivityInstance historicActivityInstance : list) {
            System.out.println(historicActivityInstance);

        }
    }

    /**
     * act_hi_taskinst
     * 历史任务查询
     * 再关联业务查询
     */
    @Test
    public void queryHiTask() {
        //
        String assign = "root2";
        HistoryService historyService = processEngine.getHistoryService();
        String businessKey = "1";
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(assign)
                .processInstanceBusinessKey(businessKey)
                //.unfinished()//未完成的任务查询
                //.finished()//已完成的任务记录
                //.processFinished()//已完成的实例
                //.processUnfinished()//未完成的实例
                //.deploymentId("1111")
                //.processDefinitionKeyLike("%AAA%")//这里得手动加%%
                .orderByTaskCreateTime().desc()
                .listPage(0, 1);
        System.out.println("--->" + list.get(0).getAssignee() + "  " + list.get(0).getTime());
        for (HistoricTaskInstance historicTaskInstance : list) {
            String processInstanceId = historicTaskInstance.getProcessInstanceId();


            List<HistoricProcessInstance> list1 = historyService.createHistoricProcessInstanceQuery()
                    .processInstanceId(processInstanceId)
                    .list();
            HistoricProcessInstance historicProcessInstance = list1.get(0);
            System.out.println("业务Id:" + historicProcessInstance.getBusinessKey());

        }
    }
    /**
     * 历史历程变量查询【略】  参考T4
     */
}
