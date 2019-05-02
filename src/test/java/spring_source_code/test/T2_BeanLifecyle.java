package spring_source_code.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_source_code.config.MainConfigLifecyle;
import spring_source_code.pojo.Dog;
import spring_source_code.pojo.IocContext;

public class T2_BeanLifecyle {

    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigLifecyle.class);

    /**
     * 单实例 略
     * 多实例 初始化容器的时候类不会创建，在获取的时候才会创建---容器关闭的时候不会销毁[销毁不归容器管-得自己销毁]
     */
    @Test
    public void singleton() {
        Object car1 = applicationContext.getBean("car1");

        //容器关闭时类销毁
        applicationContext.close();
    }

    @Test
    public void imp() {
        Object cat = applicationContext.getBean("cat");
        applicationContext.close();

    }

    /**
     * 初始化前后测试
     */
    @Test
    public void dog() {
        Dog cat = (Dog) applicationContext.getBean("dog");
        applicationContext.close();

    }

    @Test
    public void getIoc() {
        ApplicationContext applicationContext = IocContext.applicationContext;
        System.out.println("获取到的IOC容器：" + applicationContext);
    }


}
