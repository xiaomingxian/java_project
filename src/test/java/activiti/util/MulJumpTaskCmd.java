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

public class MulJumpTaskCmd implements Command<Void> {

    protected String parentId;//执行实例id
    protected String executionId;//流程实例id
    protected ActivityImpl desActivity;
    protected Map<String, Object> paramvar;
    protected ActivityImpl currentActivity;


    @Override
    public Void execute(CommandContext commandContext) {
        //获取流程实体管理器
        ExecutionEntityManager executionEntityManager = Context.getCommandContext().getExecutionEntityManager();
        //通过流程实例id找到流程实体
        ExecutionEntity executionEntity = executionEntityManager.findExecutionById(this.executionId);
        //查找顶级实例--为啥只有两次-[应该是递归]
        String id = null;
        if (executionEntity.getParent() != null) {
            executionEntity = executionEntity.getParent();
            if (executionEntity.getParent() != null) {
                executionEntity = executionEntity.getParent();
                id = executionEntity.getId();
            }
            id = executionEntity.getId();
        }

        executionEntity.setVariables(this.paramvar);
        executionEntity.setExecutions(null);
        executionEntity.setEventSource(this.currentActivity);
        executionEntity.setActivity(this.currentActivity);

        //根据 executionId 获取Task
        TaskEntityManager taskEntityManager = Context.getCommandContext().getTaskEntityManager();
        Iterator<TaskEntity> iterator = taskEntityManager.findTasksByExecutionId(id).iterator();
        while (iterator.hasNext()) {
            TaskEntity next = iterator.next();
            // 触发任务监听
            next.fireEvent("complete");
            // 删除任务原因
            taskEntityManager.deleteTask(next, "completed", false);
        }
        List<ExecutionEntity> list = executionEntityManager.findChildExecutionsByParentExecutionId(parentId);
        for (ExecutionEntity entity : list) {
            List<ExecutionEntity> parent = executionEntityManager.findChildExecutionsByParentExecutionId(entity.getId());
            for (ExecutionEntity executionEntity1 : parent) {
                executionEntity1.remove();
                Context.getCommandContext().getHistoryManager().recordActivityEnd(executionEntity1);
            }
            entity.remove();
            Context.getCommandContext().getHistoryManager().recordActivityEnd(entity);
        }
        Context.getCommandContext().getIdentityLinkEntityManager().deleteIdentityLinksByProcInstance(id);
        executionEntity.executeActivity(this.desActivity);

        return null;
    }

    public MulJumpTaskCmd(String executionId, String parentId, ActivityImpl desActivity,
                          Map<String, Object> paramvar, ActivityImpl currentActivity) {
        this.executionId = executionId;
        this.parentId = parentId;
        this.desActivity = desActivity;
        this.paramvar = paramvar;
        this.currentActivity = currentActivity;
    }
}
