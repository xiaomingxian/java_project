package spring_source_code.config;


import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import pojo.Person;
import pojo.User;
import spring_source_code.pojo.T1P;

@Configuration
@ComponentScan(value = "spring_source_code.pojo", excludeFilters = {
        //@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class}),//排除类型多种多样
        //@ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {T1PDao.class})

}, includeFilters = {
        //@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Service.class}),//排除类型多种多样
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = {MyFilter.class}),//排除类型多种多样
}
        , useDefaultFilters = false)//要用只包含得禁用默认过滤
//@Conditional(MacCondition.class)
@Import({Person.class, User.class, MyImportSelector.class, MyImportBeanDefRegister.class})
public class MainConfig {


    @Bean("t1p")
    //@Lazy//懒加载--在使用的时候才会创建--改变单实例在容器初始化的时候就创建的情况
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)//单实例：ioc容器启动就创建bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//多实例：ioc容器启动不创建bean-每获取一次就创建一次
    public T1P getT1p() {
        System.out.println("----------bean t1p 创建------");
        return new T1P("Jerry", 20);
    }

    @Conditional(WindowsCondition.class)
    @Bean("windows")
    public T1P getT1p2() {
        return new T1P("windows", 20);
    }

    @Conditional(MacCondition.class)
    @Bean("mac")
    public T1P getT1p3() {
        return new T1P("mac", 20);
    }

    @Conditional(LinuxCondition.class)
    @Bean("linux")
    public T1P getT1p4() {
        return new T1P("linux", 20);
    }

    @Bean
    public MsgFactory msgFactory() {
        return new MsgFactory();
    }

}
