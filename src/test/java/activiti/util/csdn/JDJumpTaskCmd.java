package activiti.util.csdn;


import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ExecutionEntityManager;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmException;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ScopeImpl;
import org.activiti.engine.impl.pvm.runtime.InterpretableExecution;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description: 自由跳转流程
 * @create: 2018-06-13 09:22
 **/
public class JDJumpTaskCmd implements Command<Void> {

    protected String taskId;//任务id
    protected String executionId;//执行实例id
    protected String parentId;//流程实例id
    protected ActivityImpl desActivity;//目标节点
    protected Map<String, Object> paramvar;//变量
    protected ActivityImpl currentActivity;//当前的节点

    @Override
    public Void execute(CommandContext commandContext) {

        ExecutionEntityManager executionEntityManager = Context
                .getCommandContext().getExecutionEntityManager();
        ExecutionEntity executionEntity = executionEntityManager
                .findExecutionById(executionId);
        //寻找根路径
        String id = null;
        if (executionEntity.getParent() != null) {
            executionEntity = executionEntity.getParent();

            if (executionEntity.getParent() != null) {
                executionEntity = executionEntity.getParent();
                id = executionEntity.getId();
            }

            id = executionEntity.getId();
        }
        //设置相关变量
        executionEntity.setVariables(paramvar);
        //executionEntity.setExecutions(null);
        executionEntity.setEventSource(this.currentActivity);
        executionEntity.setActivity(this.currentActivity);
        // 根据executionId 获取Task
        Iterator<TaskEntity> localIterator = Context.getCommandContext()
                .getTaskEntityManager().findTasksByProcessInstanceId(parentId).iterator();
        //删除无用的工作项
        while (localIterator.hasNext()) {
            TaskEntity taskEntity = (TaskEntity) localIterator.next();
            System.err.println("==================" + taskEntity.getId());
            if(taskId.equals(taskEntity.getId())) {
                // 触发任务监听
                taskEntity.fireEvent("complete");
                // 删除任务的原因
                Context.getCommandContext().getTaskEntityManager()
                        .deleteTask(taskEntity, "completed", false);
            }else {
                // 删除任务的原因
                Context.getCommandContext().getTaskEntityManager()
                        .deleteTask(taskEntity, "deleted", false);
            }

        }
        //删除相关执行子路径，只保留根执行路径
        List<ExecutionEntity> list = executionEntityManager
                .findChildExecutionsByParentExecutionId(parentId);
        for (ExecutionEntity executionEntity2 : list) {
            ExecutionEntity findExecutionById = executionEntityManager.findExecutionById(executionEntity2.getId());

            List<ExecutionEntity> parent = executionEntityManager
                    .findChildExecutionsByParentExecutionId(executionEntity2
                            .getId());
            for (ExecutionEntity executionEntity3 : parent) {
                executionEntity3.remove();
                System.err.println(executionEntity3.getId()
                        + "----------------->>>>>>>>>>");
                Context.getCommandContext().getHistoryManager()
                        .recordActivityEnd(executionEntity3);

            }

            executionEntity2.remove();
            Context.getCommandContext().getHistoryManager().recordActivityEnd(executionEntity2);
            System.err.println(findExecutionById + "----------------->>>>>>>>>>");


        }

        commandContext
                .getIdentityLinkEntityManager().deleteIdentityLinksByProcInstance(parentId);
        //要激活交路径
        executionEntity.setActive(true);
        //去掉无用的变量，不去掉，会导致很多莫名奇妙的问题
        executionEntity.removeVariable("loopCounter");
        //去掉多实例的变量，如果变量不知道是啥，自己从节点定义里查
        executionEntity.removeVariable("cdp_atuser");
        //触发事件监听器
        this.execute(executionEntity);
        InterpretableExecution propagatingExecution = null;
        if (this.desActivity.isScope()) {
            propagatingExecution = (InterpretableExecution) executionEntity.createExecution();
            executionEntity.setTransition(null);
            executionEntity.setActivity(null);
            executionEntity.setActive(false);
            // log.debug("create scope: parent {} continues as execution {}", execution, propagatingExecution);
            propagatingExecution.initialize();

        } else {
            propagatingExecution = executionEntity;
        }


        propagatingExecution.executeActivity(this.desActivity);

        return null;
    }


    protected ScopeImpl getScope(InterpretableExecution execution) {
        return (ScopeImpl) execution.getActivity();
    }

    /*
      触发事件监听器
     */
    public void execute(InterpretableExecution execution) {
        ScopeImpl scope = getScope(execution);
        List<ExecutionListener> exectionListeners = scope.getExecutionListeners(getEventName());
        for (ExecutionListener listener : exectionListeners) {
            execution.setEventName(getEventName());
            execution.setEventSource(scope);
            try {
                listener.notify(execution);
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new PvmException("couldn't execute event listener : " + e.getMessage(), e);
            }

        }
    }

    protected String getEventName() {
        return org.activiti.engine.impl.pvm.PvmEvent.EVENTNAME_END;
    }

    /**
     * 构造参数 可以根据自己的业务需要添加更多的字段
     * @param taskId
     * @param executionId
     * @param desActivity
     * @param paramvar
     * @param currentActivity
     */
    public JDJumpTaskCmd(String taskId,String executionId, String parentId,
                         ActivityImpl desActivity, Map<String, Object> paramvar,
                         ActivityImpl currentActivity) {
        this.taskId=taskId;
        this.executionId = executionId;
        this.parentId = parentId;
        this.desActivity = desActivity;
        this.paramvar = paramvar;
        this.currentActivity = currentActivity;

    }
}
