package controller.activiti;


import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("activiti")
public class ActivitiController {

    @Autowired
    protected IdentityService identityService;

    @Autowired
    protected RepositoryService repositoryService;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected ManagementService managementService;

    @Autowired
    protected ProcessEngineConfigurationImpl processEngineConfiguration;


    @RequestMapping("test")
    public Map test() {
        HashMap<Object, Object> map = new HashMap<>();
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        Deployment deploy = processEngine.getRepositoryService()
                .createDeployment()
                .addClasspathResource("bpmn/shenqing.bpmn")
                .addClasspathResource("bpmn/shenqing.png")
                .deploy();
        map.put("deploy", deploy);
        return map;


    }


}
