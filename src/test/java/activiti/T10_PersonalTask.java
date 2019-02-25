package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T10_PersonalTask {

    @Autowired
    private ProcessEngine processEngine;

    @Test
    public void deploy() {

        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/Personal_Listener.bpmn")
                .addClasspathResource("bpmn/Personal_Listener.png")
                .deploy();
        System.out.println("---->发布成功|name:" + deploy.getName() + " " + deploy.getId());
    }


    //    个人任务分配的几种方式
    //    1.分配到具体人【略】
    //    2.使用流程变量【可以在开启流程的时候设置变量值】#{},${}--->这两种形式一样
    //        可以在查询当前任务时指定下一个办理人，也可以在办理下一个任务前指定办理人
    //    3.使用监听类
    @Test
    public void method2() {
        ////    开启流程设置下一个办理人  eg:设置当前用户为办理人
        RuntimeService runtimeService = processEngine.getRuntimeService();
        TaskService taskService = processEngine.getTaskService();
        HashMap<String, Object> map = new HashMap<>();
        map.put("user", "王五");
        //开启流程的时候指定下一个办理人
        //    执行任务再设置下一个办理人 eg:根据当前用户获取他的上级作为办理人
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Varibles", map);
        //System.out.println("开启流程成功：" + processInstance.getId());
        //    查询任务
        //method1:在查询时指定下一个办理人
        //taskService.setVariables("127505", map);
        //查询的时候多加几个限定条件---考虑到不同流程办理人重名问题 等
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("Varibles")
                .taskAssignee("王五").singleResult();

        //System.out.println("当前代办人" + task.getAssignee());


        //    办理当前任务
        taskService.complete(task.getId());
        //method2:办理任务的同时，指定下一个办理人
        //taskService.complete(task.getId(), map);
    }

    /**
     * 创建监听类
     * 此例为流程参数与监听器结合---但是监听器作用在了所有任务节点上？？为啥
     */
    @Test
    public void method3() {
        //RuntimeService runtimeService = processEngine.getRuntimeService();
        //String key = "Listener";
        //Map<String, Object> map = new HashMap<>();
        //map.put("user", "张三");
        //ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        //System.out.println("流程启动：id:" + processInstance.getId());

        //查询办理任务
        TaskService taskService = processEngine.getTaskService();
        //Task task = taskService.createTaskQuery()
        List<Task> tasks = taskService.createTaskQuery()
                //.processDefinitionKey("Listener")
                .taskAssignee("王五")
                //.singleResult();
                .listPage(0, 10);
        System.out.println(tasks);
        //taskService.setAssignee(task.getId(),"assignName");//既没有设置流程变量-又没有设置监听器就可以使用这种方式  待确认【没有指定办理人与监听器应该就不会有task】
        //taskService.complete(task.getId());

    }

}
