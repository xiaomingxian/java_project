package activiti;

import activiti.pojo.User;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricVariableInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 涉及到的表
 * 运行时变量表
 * 历史变量表
 * 以上2表数据遵循map集合特点 key相同value被覆盖 版本会+1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T4_Variables {

    @Autowired
    private ProcessEngine processEngine;

    /**
     * 1.
     * 启动流程并设置流程变量
     */
    @Test
    public void setVariables1() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "task_public";
        HashMap<String, Object> map = new HashMap<>();
        map.put("请假天数", 3);
        map.put("请假原因", "回家");
        map.put("请假时间", new Date());
        runtimeService.startProcessInstanceByKey(key, map);

    }
    //设置流程变量时使用Loca..进行设置
    //通过ExcutionId设置 不会覆盖之前，会重新创建一个【多分支情况下使用？】
    //使用TaskId设置，会在变量表中绑定taskId  不适用Local此字段为null

    /**
     * 2.
     * 单独设置变量
     * 根据运行时流程实例id单独设置，发生变化的value被替换，版本【rev】+1
     * 序列化对象在记录表中存的时二进制表中数据的主键【二进制表中会增加2条数据  运行时变量和历史变量】
     */
    @Test
    public void setVaribles2() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String excutionId = "42501";
        HashMap<String, Object> map = new HashMap<>();
        map.put("请假天数", 3);
        map.put("请假原因", "回家2");
        map.put("请假时间", new Date());
        map.put("", new User("小红", 20, "女"));
        runtimeService.setVariablesLocal(excutionId, map);
        System.out.println("流程变量值更新成功");

    }

    /**
     * 3.
     * 通过taskID设置变量：场景【领导审批时】
     */
    @Test
    public void setVaribles3() {
        TaskService taskService = processEngine.getTaskService();
        String taskId = "42507";
        HashMap<String, Object> map = new HashMap<>();
        map.put("审批意见", "批准");
        taskService.setVariables(taskId, map);


    }

    /**
     * 获取流程变量
     * 通过运行时流程Id获取  excutionId
     */
    @Test
    public void getVaribles() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String excutionId = "42501";
        //Map<String, Object> variables = runtimeService.getVariables(excutionId);
        //System.out.println("---->流程变量键值对：" + variables);
        Object startTime = runtimeService.getVariable(excutionId, "请假时间");
        System.out.println("---->获取特定key:" + ((Date) startTime).toLocaleString());
        //    获取序列化对象

    }

    /**
     * 使用任务id获取变量
     * 任务id不存在时 可以准确的捕获异常进行提示---这块实际上还是根据excutionId去查询变量【根据sql可知】
     */
    @Test
    public void getVaribles2() {
        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> variables = taskService.getVariables("42507");
        System.out.println(variables);
    }

    /**
     * 获取历史变量
     */
    @Test
    public void getHiVariables() {
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricVariableInstance> list = historyService.createHistoricVariableInstanceQuery()
                .executionId("42501")//还有其他查询条件
                .list();
        System.out.println("--->根据excutionId获取历史变量：" + list);
    }

}
