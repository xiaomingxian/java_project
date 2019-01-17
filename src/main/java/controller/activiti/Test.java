package controller.activiti;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

//@RestController
//@RequestMapping("activiti")
public class Test {

    //核心类下面的实例都可以通过它获取
    @Autowired
    private ProcessEngine processEngine;

    //@Autowired
    //private RepositoryService repositoryService;
    //
    //
    //@Autowired
    //private RuntimeService runtimeService;
    //
    //
    //@Autowired
    //private TaskService taskService;
    //
    //
    //@Autowired
    //protected HistoryService historyService;
    //
    //
    //@Autowired
    //private ManagementService managementService;
    //
    //
    //@Autowired
    //private IdentityService identityService;
    //
    //
    //@Autowired
    //private FormService formService;


    //@RequestMapping("serviceLook")
    public Map serviceLook() {
        HashMap map = new HashMap();
        //
        //List<Model> models = repositoryService.createModelQuery().list();
        //List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery().list();
        //List<Task> tasks = taskService.createTaskQuery().list();
        //List<HistoricDetail> historicDetails = historyService.createHistoricDetailQuery().list();
        //List<Job> jobs = managementService.createJobQuery().list();
        //List<User> list = identityService.createUserQuery().list();
        //String fs = formService.toString();
        //
        //map.put("repositoryService", models);
        //map.put("runtimeService", processInstances);
        //map.put("taskService", tasks);
        //map.put("historyService", historicDetails);
        //map.put("managementService", jobs);
        //map.put("identityService", list);
        //map.put("formService", fs);
        return map;
    }

    /**
     * 启动流程实例
     *
     * @return
     */
    @RequestMapping("process")
    public Map process() {
        HashMap map = new HashMap();
        try {
            //开启流程
            ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("task_public");
            //map.put("processInstance", processInstance);
            map.put("status", "流程开启成功");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "流程开启失败");
        }
        return map;
    }

    /**
     * 审批
     */
    @Autowired
    public Map pass() {
        //查询用户角色与任务角色表进行匹配

        HashMap map = new HashMap();


        return map;

    }

}
