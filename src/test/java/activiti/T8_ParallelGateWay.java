package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T8_ParallelGateWay {
    @Autowired
    private ProcessEngine processEngine;


    /**
     * 并行网关任务处理
     * eg:货到付款
     */
    @Test
    public void doTask() {
        TaskService taskService = processEngine.getTaskService();


        List<Task> list = taskService.createTaskQuery()
                .taskAssignee("客户").list();
        for (Task task : list) {
            System.out.println(
                    "---->任务Id:" + task.getId() +
                            " 待办人员" + task.getAssignee() +
                            " 活动名称:" + task.getName()
            );
        }
        //taskService.complete("105002");

    }


}
