package spring_source_code.test;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_source_code.config.MainConfig;
import spring_source_code.pojo.T1P;

import java.util.Arrays;

public class T1_Base {
    AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

    /**
     * 配置文件方式
     */
    @Test
    public void t1() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring/springbean.xml");

        T1P t1 = (T1P) applicationContext.getBean("t1p");
        System.out.println(t1);
    }

    /**
     * 配置类
     */
    @Test
    public void annotation() {
        Object t1p = annotationConfigApplicationContext.getBean("t1p");
        System.out.println(t1p);

        //获取工厂中 bean名称
        String[] beanNamesForType = annotationConfigApplicationContext.getBeanNamesForType(T1P.class);
        Arrays.stream(beanNamesForType).forEach(System.out::println);
    }

    /**
     * 包扫描
     */
    @Test
    public void packageScan() {

        //获取工厂中 bean名称
        String[] beanNamesForType = annotationConfigApplicationContext.getBeanDefinitionNames();
        Arrays.stream(beanNamesForType).forEach(System.out::println);

    }


    /**
     * bean创建时机
     */
    @Test
    public void beanCreate() {
        //单实例


        //    多实例
        Object t1p = annotationConfigApplicationContext.getBean("t1p");
        Object t1p1 = annotationConfigApplicationContext.getBean("t1p");
        //单实例情况
        System.out.println(t1p == t1p1);
    }

    @Test
    public void condition() {
        //设置操作系统名称  -Dos.name=Linux

        //ClassLoader classLoader = annotationConfigApplicationContext.getClassLoader();
        //System.out.println(classLoader);
        //ConfigurableEnvironment environment = annotationConfigApplicationContext.getEnvironment();
        //String property = environment.getProperty("os.name");
        //System.out.println(property);

        //T1P bean = (T1P) annotationConfigApplicationContext.getBean("mac");
        //System.out.println("----->获取到的mac bean:" + bean);

        //T1P linux = (T1P) annotationConfigApplicationContext.getBean("linux");
        //System.out.println("----->获取到的linux bean:" + linux);
    }

    @Test
    public void importTest() {
        //@import 注解导入
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();

        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
    }
    @Test
    public void factory(){
        Object msg1 = annotationConfigApplicationContext.getBean("msgFactory");
        Object msg2= annotationConfigApplicationContext.getBean("msgFactory");
        System.out.println(msg1.getClass());
        System.out.println(msg1==msg2);
        Object msgFactory_f = annotationConfigApplicationContext.getBean("&msgFactory");
        System.out.println(msgFactory_f.getClass());
    }
}
