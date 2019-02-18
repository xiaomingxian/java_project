package activiti_listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class GroupListener implements TaskListener {
    /**
     * 如果是组任务，
     * 根据角色进行区分
     * 根据当前用户角色查询到他的上级角色【对应的用户，添加到用户任务组里】
     *
     * @param delegateTask
     */
    @Override
    public void notify(DelegateTask delegateTask) {

    }
}
