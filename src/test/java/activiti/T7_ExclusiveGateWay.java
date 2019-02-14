package activiti;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * 排他网关
 * 相当于 if else if else if else
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T7_ExclusiveGateWay {
    @Autowired
    private ProcessEngine processEngine;


    @Test
    public void doTask() {
        TaskService taskService = processEngine.getTaskService();

        //指定参数
        HashMap<String, Object> map = new HashMap<>();
        map.put("money", 2000);

        Task task = taskService.createTaskQuery()
                .taskAssignee("张三").singleResult();
        taskService.complete(task.getId(), map);
        //taskService.complete(task.getId());


    }


}
