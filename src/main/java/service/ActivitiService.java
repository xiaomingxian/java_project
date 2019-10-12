package service;

import java.util.Map;

public interface ActivitiService {
    void deploy(String source);

    void start(String instanceKey, Map<String, Object> vars);

    void doTask(String user, Map<String, Object> vars);
}
