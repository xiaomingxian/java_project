package spring_source_code.pojo;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class IocContext implements ApplicationContextAware {
    /**
     * 在此类初始化之前 BeanPostProcessor.postProcessAfterInitialization
     * 判断此类有没有实现  ...Aware 接口
     * 如果是就调用实现的  setApplicationContext 将Ioc容器传进去
     */
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("----->ioc容器：" + applicationContext);
        this.applicationContext = applicationContext;
    }
}
