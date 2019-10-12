package service.impl;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ActivitiService;

import java.util.Map;


@Service
@Transactional
public class ActivitiServiceImpl implements ActivitiService {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Override
    public void deploy(String source) {
        repositoryService.createDeployment()
                .addClasspathResource(source)
                .deploy();
    }

    @Override
    public void start(String instanceKey, Map<String, Object> vars) {
        runtimeService.startProcessInstanceByKey(instanceKey, vars);
    }

    @Override
    public void doTask(String user, Map<String, Object> vars) {
        Task task = taskService.createTaskQuery().taskAssignee(user).singleResult();

        taskService.complete(task.getId(), vars);
    }



}
