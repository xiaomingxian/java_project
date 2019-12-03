package springx.context;

import springx.beans.BeanFactoryX;
import springx.beans.config.BeanDefinitionX;
import springx.beans.support.BeanDefinitionReaderX;
import springx.beans.support.DefaultListableBeanFactoryX;

import java.util.List;

/**
 * AnnotationContext...
 * XmlContext...等等
 */
public class ApplicationX extends DefaultListableBeanFactoryX implements BeanFactoryX {


    private String[] locations;

    public ApplicationX(String... locations) {
        this.locations = locations;
        refresh();
    }

    @Override
    public Object getBean(String className) {
        return null;
    }



    @Override
    public void refresh() {
        //1 定位配置文件
        BeanDefinitionReaderX beanDefinitionReaderX = new BeanDefinitionReaderX(this.locations);


        //2 加载配置文件，扫描相关的类，把他们封装成BeanDefinition
        List<BeanDefinitionX> beanDefinitionXES = beanDefinitionReaderX.loadBeanDefinitions();

        //3 注册，把配置信息放到容器类里(自定义IOC容器)
        doRegisterBeanDefinition(beanDefinitionXES);


        //4 不不是延迟加载的类初始化
        doAutowired();

    }

    private void doAutowired() {



    }

    private void doRegisterBeanDefinition(List<BeanDefinitionX> beanDefinitionXES) {


    }



}
