package spring_source_code.test;

import org.junit.Test;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_source_code.pojo.MyBeanFactoryPostProcessor;

public class T7_OtherTest {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBeanFactoryPostProcessor.class);

    /**
     * bean工厂后置处理器
     * <p>
     * 观察bean定义后置处理器与beanFactory后置处理器的执行顺序
     */
    @Test
    public void beanFactoryTest() {
        //List<BeanFactoryPostProcessor> beanFactoryPostProcessors = applicationContext.getBeanFactoryPostProcessors();
        //System.out.println(beanFactoryPostProcessors.size());//为啥是0

    }

    @Test
    public void listener() {
        //自定义事件发布
        applicationContext.publishEvent(new ApplicationEvent("自定义事件发布") {
        });
        applicationContext.close();
    }

    @Test
    public void tt() {
        applicationContext.refresh();
    }

    @Autowired
    BeanFactory beanFactory;
    @Autowired
    Environment environment;

}
