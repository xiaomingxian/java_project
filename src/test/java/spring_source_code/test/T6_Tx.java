package spring_source_code.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_source_code.config.MainConfigTx;
import spring_source_code.tx.MyService;

public class T6_Tx {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigTx.class);


    @Test
    public void t1() {
        MyService myService = (MyService) applicationContext.getBean("myService");
        myService.insert();
    }

    @Test
    public void yuanli() {
        //    1. 通过 @EnableTransactionManagement 给容器中注入2个组件：
        //              AutoProxyRegistrar
        //              ProxyTransactionManagementConfiguration

        //    2.AutoProxyRegistrar
        //              给容器中注册一个   InfrastructureAdvisorAutoProxyCreator 后置处理器[功能与aop的后置处理器类似]组件
        //                  利用这个后置处理器在创建对象以后，包装对象，返回一个代理对象[增强器]，代理对象利用拦截器链对增强方法进行执行[如果没有增强就执行原方法]
        //    3.ProxyTransactionManagementConfiguration
        //              给容器注册事物增强器  事物增强器要用事物注解信息，AnnotationTransactionAttributeSource 来解析事物注解
        //              事物拦截器 TransactionInterceptor 保存了事物属性信息 是一个 MethodInterceptor 在目标方法执行的时候执行拦截器链
        //                      在执行目标方法的时候：
        //                          先获取事物相关属性
        //                          再获取 事物管理器 PlatformTransactionManager[默认，如果没有指定任何的管理器，会从容器中按照类型取一个(配置类里注入的)]
        //              执行目标方法
        //                  如果异常，获取到事物管理器，利用事物管理器回滚操作
        //                  如果正常，获取到事物管理器，利用事物管理器提交事物
    }

}
