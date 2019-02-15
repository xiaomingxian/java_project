package activiti.pojo;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.apache.http.protocol.RequestContent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 单人任务的监听器
 */
public class MyTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("---->触发监听器");
        //从session中获取用户信息
        //HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
        //        .getRequest();
        //HttpSession session = request.getSession();
        //这里的数据如何动态---每个用户从session获取到自己再获取到上级领导
        String assign = "张三";
        delegateTask.setAssignee(assign);

    }
}
