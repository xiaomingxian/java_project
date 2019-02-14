package activiti;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext-acitiviti.cfg.xml"})
public class T9_ReceiveTask {
    @Autowired
    private ProcessEngine processEngine;


    /**
     * 发布流程
     */
    @Test
    public void deploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("bpmn/ReceiveTask.bpmn")
                .addClasspathResource("bpmn/ReceiveTask.png")
                .deploy();
        System.out.println("--->流程id:" + deploy.getId());
    }

    /**
     * 开启流程
     */
    @Test
    public void startProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String processkey = "SaleCountSend";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processkey);
        System.out.println("---->流程实例id：" + processInstance.getId());
    }


    /**
     * 流程图的另一种画法
     * 统计下连2跳线【使用流程变量控制，统计失败直接结束】
     * 发送下连2跳线【使用流程变量控制，发送失败直接结束】
     */


    /**
     * 模拟接收任务自动执行
     * 获取销售量
     */
    @Test
    public void getSaleCount() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceId("115001")
                .activityId("_4")
                .singleResult();
        String executionId = execution.getId();

        Integer count = 100000;//模拟表中数据
        runtimeService.setVariable(executionId, "今日销售量", count);
        //向后执行一步
        runtimeService.signal(executionId);

    }

    /**
     * 发送短信
     */
    @Test
    public void sendMessage() {

        RuntimeService runtimeService = processEngine.getRuntimeService();
        Execution execution = runtimeService.createExecutionQuery()
                .processInstanceId("115001")
                .activityId("_5")
                .singleResult();
        String executionId = execution.getId();
        System.out.println(executionId);
        Boolean flag = false;
        int num = 0;
        do {
            flag = send();
            num++;
            if (num == 10) {
                //实际中应该将对应的数据写入数据库
                System.out.println("尝试发送十次失败，以放弃");
                break;
            }
            //发送短信。。。的函数
            //如果发送失败重复发送
        } while (!flag);
        //不管发送成功与否流程都结束
        runtimeService.signal(executionId);
        System.out.println("流程结束。。。。。");

    }

    public Boolean send() {
        return true;
    }
}


