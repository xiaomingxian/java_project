package activiti.util;

import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntityManager;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 分支节点跳转
 */
public class ParallelJumpTaskCmd implements Command<Void> {
    protected String parentId;//执行实例id
    protected String executionId;//流程实例id
    protected ActivityImpl desActivity;
    protected Map<String, Object> paramvar;
    protected ActivityImpl currentActivity;

    /**
     *
     */
    public Void execute(CommandContext commandContext) {
        //流程实体管理器
        ExecutionEntityManager executionEntityManager = Context.getCommandContext().getExecutionEntityManager();
        //流程实例
        ExecutionEntity executionEntity = executionEntityManager.findExecutionById(executionId);

        //如果流程有父节点就把父节点作为节点
        String id = null;
        if (executionEntity.getParent() != null) {
            executionEntity = executionEntity.getParent();
            if (executionEntity.getParent() != null) {
                executionEntity = executionEntity.getParent();
                id = executionEntity.getId();
            } else {
                id = executionEntity.getId();
            }
        }
        //
        executionEntity.setVariables(paramvar);
        executionEntity.setEventSource(this.currentActivity);
        executionEntity.setActivity(this.currentActivity);
        //根据 executionId获取Task
        TaskEntityManager taskEntityManager = Context.getCommandContext().getTaskEntityManager();
        List<TaskEntity> tasksByExecutions = taskEntityManager.findTasksByExecutionId(executionId);
        Iterator<TaskEntity> iterator = tasksByExecutions.iterator();
        while (iterator.hasNext()) {
            TaskEntity next = iterator.next();
            //    触发任务监听
            next.fireEvent("complete");
            //    删除任务的原因
            taskEntityManager.deleteTask(next, "test-reason", false);
        }
        //删除实例数据
        List<ExecutionEntity> list = executionEntityManager.findChildExecutionsByParentExecutionId(parentId);
        for (ExecutionEntity e : list) {
            e.remove();
        }
        executionEntity.executeActivity(this.desActivity);

        return null;
    }

    /**
     * 构造参数 可以根据自己的业务需要添加更多的字段
     * 分享牛原创(尊重原创 转载对的时候第一行请注明，转载出处来自分享牛http://blog.csdn.net/qq_30739519)
     *
     * @param executionId
     * @param desActivity
     * @param paramvar
     * @param currentActivity
     */
    public ParallelJumpTaskCmd(String executionId, ActivityImpl desActivity,
                               Map<String, Object> paramvar, ActivityImpl currentActivity) {
        this.executionId = executionId;
        this.desActivity = desActivity;
        this.paramvar = paramvar;
        this.currentActivity = currentActivity;
    }
}