package spring_source_code.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_source_code.config.MainConfigProperty;
import spring_source_code.pojo.Proper;

import java.util.Arrays;

public class T3_PropertyTest {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigProperty.class);

    public void getBeanDefinitionNames() {
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);

    }

    @Test
    public void setProperty() {
        Proper proper = (Proper) applicationContext.getBean("spring_source_code.pojo.Proper");
        System.out.println(proper);
        //从运行环境中获取变量
        String property = applicationContext.getEnvironment().getProperty("address");
        System.out.println(" 从运行环境中获取变量：" + property);

    }

    @Test
    public void xxxAware() {
        applicationContext.getBean("spring_source_code.pojo.MyAware");
    }
}
