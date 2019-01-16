package controller.activiti;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("activiti")
public class ActivitiController {


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
