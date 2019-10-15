package activiti.code;


import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T19_FormTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Test
    public void deploy() {

        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/form/FormTest.bpmn")
                //.addClasspathResource("bpmn/huiqian/SubHuiQian.png")
                .deploy();


        //InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("bpmn/form/FormTest.bpmn");
        //Deployment deploy = repositoryService.createDeployment()
        //        .name("FormTest").addInputStream("FormTest",resourceAsStream).deploy();

        System.out.println("------->部署实例：" + deploy.getId());
    }

    /**
     * 根据流程定义id从开始事件获取信息
     * 根据任务id从任务节点获取信息
     */
    @Test
    public void start() {

        runtimeService.startProcessInstanceByKey("myProcess");

    }

    /**
     * 获取下一节点
     */
    @Test
    public void getNext() {
        Task task = taskService.createTaskQuery().taskId("5004").singleResult();
        String processDefinitionId = task.getProcessDefinitionId();

        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        //StartFormData startFormData = formService.getStartFormData(processDefinitionId);



        //System.out.println("----+>" + startFormData.getFormProperties().size());
        System.out.println("----+>" + taskFormData.getFormProperties().size());

        //startFormData.getFormProperties().stream().forEach(i -> {
        //    System.out.println(i.getId() + "  " + i.getName());
        //});
        taskFormData.getFormProperties().stream().forEach(i -> {
            System.out.println(i.getId() + "  " + i.getName());
        });


    }

}
