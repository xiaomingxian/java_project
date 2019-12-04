package springx.context;

import springx.beans.BeanFactoryX;
import springx.beans.BeanWrapperX;
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
    public Object getBean(String beanName) {

        //0 doGetBean
        /**
         * 为什么要先初始化在注入[而不是同时做]：因为要解决循环依赖的问题
         */

        //1 初始化(仅初始化，不注入属性值)
        instatiateBean(beanName, new BeanDefinitionReaderX());

        //2 注入(注入属性值)
        populateBean(beanName, new BeanWrapperX());



        return null;
    }

    @Override
    public void refresh() {
        //1 定位配置文件
        BeanDefinitionReaderX beanDefinitionReaderX = new BeanDefinitionReaderX(this.locations);


        //2 加载配置文件，扫描相关的类，把他们封装成BeanDefinition
        List<BeanDefinitionX> beanDefinitionXES = beanDefinitionReaderX.loadBeanDefinitions();

        //3 注册，把配置信息放到容器类里(自定义IOC容器[类定义信息容器])
        doRegisterBeanDefinition(beanDefinitionXES);

        //4 不不是延迟加载的类初始化
        doAutowired();

    }


    private void populateBean(String beanName, BeanWrapperX beanWrapperX) {

    }

    private void instatiateBean(String beanName, BeanDefinitionReaderX beanDefinitionReaderX) {


    }


    private void doAutowired() {

        super.beanDefinitionMap.entrySet().stream().forEach(entry -> {
            String key = entry.getKey();
            BeanDefinitionX beanDefinitionX = entry.getValue();
            //非延迟加载
            if (!beanDefinitionX.isLazyInit()) {
                getBean(key);
            }
        });

    }

    private void doRegisterBeanDefinition(List<BeanDefinitionX> beanDefinitionXES) {

        beanDefinitionXES.stream().forEach(beanDefinitionX -> {
            super.beanDefinitionMap.put(beanDefinitionX.getFactoryBeanName(), beanDefinitionX);
        });
    }



}
