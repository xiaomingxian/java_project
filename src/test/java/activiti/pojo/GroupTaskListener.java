package activiti.pojo;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 组任务监听器
 */
public class GroupTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("--------->触发组监听器");
        delegateTask.addCandidateUser("Jack");
        delegateTask.addCandidateUser("Robin");
        delegateTask.addCandidateUser("Pony");
    }
}
