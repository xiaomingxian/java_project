package activiti.pojo;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
//这个对象是在Activiti中通过new创建的  @Component这个注解不管用
//通过获取spring容器上下文来得到Service

public class AssignListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {

        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
                .getRequest();
        //从session中获取到当前用户再查询他的上级
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        UserService bean = webApplicationContext.getBean(UserService.class);
        //再通过user获取上级 名称
        String assign = "李四";
        delegateTask.setAssignee(assign);

    }
}
