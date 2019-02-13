
package activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * ‚
 *
 * @author scw
 * @create 2018-01-15 11:04
 * @desc 用于进行演示Activiti的首例程序，即描述如何在代码中实现学生进行请假申请，班主任审核，教务处审核
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T1_ActivitiTest {
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 1、部署流程
     *
     * 2、启动流程实例
     * 3、请假人发出请假申请
     * 4、班主任查看任务
     * 5、班主任审批
     * 6、最终的教务处Boss审批
     */
    /**
     * 1：部署一个Activiti流程
     * 运行成功后，查看之前的数据库表，就会发现多了很多内容
     */
    @Test
    public void creatActivitiTask() {

        //加载的那两个内容就是我们之前已经弄好的基础内容哦。
        //得到了流程引擎
        //.addClasspathResource("bpmn/q2.bpmn")  bpmn格式才会 act_re_procdef插入数据
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deploy = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("bpmn/CountSalary.zip")
                //.addClasspathResource("bpmn/task_public.bpmn")
                //.addClasspathResource("bpmn/task_public.png")
                .deploy();

        System.out.println(deploy.getId());
    }

    /**
     * 2：启动流程实例
     */
    @Test
    public void testStartProcessInstance() {
        //启动一次act_ru_task会增加一条数据--excution也会 ru_index..也会
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getRuntimeService()
                .startProcessInstanceById("task_public:2:25004");  //这个是查看数据库中act_re_procdef表
    }

    /**
     * 查询任务---根据办理人查询
     */
    @Test
    public void getTask() {
        TaskService taskService = processEngine.getTaskService();
        String assign = "root,admin,tom";
        List<Task> list = taskService.createTaskQuery().taskAssignee(assign).list();
        System.out.println("--------查询任务--------");
        for (Task task : list) {
            System.out.println(
                    "  任务id:" + task.getId() + "  " +
                            "  " + task.getAssignee() + "  " +
                            "  " + task.getName() + "  " +
                            "  流程定义id:" + task.getProcessDefinitionId() + "  " +
                            "  流程实例id:" + task.getProcessInstanceId() +
                            "  执行实例id:" + task.getExecutionId() +
                            "  执行实例id:" + task.getTaskDefinitionKey()
            );

            //完成节点任务
            if (StringUtils.isNotBlank(task.getId())) {
                taskService.complete(task.getId());
            }

        }
    }

    /**
     * 完成请假申请
     */
    @Test
    public void testQingjia() {
        //方式二
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete("12504"); //查看act_ru_task表---参数为上一步查询到的任务id
        //会删除ACT_RU_TASK对应的id
    }

    /**
     * 小明学习的班主任小毛查询当前正在执行任务
     */
    @Test
    public void testQueryTask() {
        //下面代码中的小毛，就是我们之前设计那个流程图中添加的班主任内容
        //查询task信息---ACT_RU_TASK
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        List<Task> tasks = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee("jerry")
                .list();
        for (Task task : tasks) {
            System.out.println(task.getName());
        }
    }

    /**
     * 班主任小毛完成任务
     */
    @Test
    public void testFinishTask_manager() {
        //act_ru_task根据name:UserTask1 或者  TASK_DEF_KEY_获取对应id
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        engine.getTaskService()
                .complete("17502"); //查看act_ru_task数据表
    }

    /**
     * 教务处的大毛完成的任务
     */
    @Test
    public void testFinishTask_Boss() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        processEngine.getTaskService()
                .complete("20002");  //查看act_ru_task数据表
    }
}
