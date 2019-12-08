package springx.context;

import springx.annotation.AutowiredX;
import springx.annotation.ControllerX;
import springx.annotation.ServiceX;
import springx.beans.BeanFactoryX;
import springx.beans.BeanWrapperX;
import springx.beans.config.BeanDefinitionX;
import springx.beans.support.BeanDefinitionReaderX;
import springx.beans.support.DefaultListableBeanFactoryX;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AnnotationContext...
 * XmlContext...等等
 */
public class ApplicationX extends DefaultListableBeanFactoryX implements BeanFactoryX {

    public ApplicationX() {
    }

    private String[] locations;


    //单例对象容器(单例IOC容器缓存)
    Map<String, Object> singletonContainer = new ConcurrentHashMap();

    //BeanWrapper容器(通用IOC容器)
    private Map<String, BeanWrapperX> factoryBeanInstanceCache = new ConcurrentHashMap<>();

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
        BeanWrapperX beanWrapperX = instatiateBean(beanName, beanDefinitionMap.get(beanName));

        //2 将BeanWrapper保存到 wrapper容器中
        factoryBeanInstanceCache.put(beanName, beanWrapperX);


        //3 注入(注入属性值)
        populateBean(beanName, beanWrapperX);



        return factoryBeanInstanceCache.get(beanName).getWrapperInstance();
    }

    @Override
    public void refresh() {
        //1 定位配置文件
        BeanDefinitionReaderX beanDefinitionReaderX = new BeanDefinitionReaderX(this.locations);

        //2 加载配置文件，扫描相关的类，把他们封装成BeanDefinition
        List<BeanDefinitionX> beanDefinitionXES = beanDefinitionReaderX.loadBeanDefinitions();

        //3 注册，把配置信息放到容器类里(自定义IOC容器[类定义信息容器])
        doRegisterBeanDefinition(beanDefinitionXES);

        //4 不是延迟加载的类初始化
        doAutowired();

    }


    private void populateBean(String beanName, BeanWrapperX beanWrapperX) {
        //获取到Bean实例
        Object instance = beanWrapperX.getWrapperInstance();
        if (instance == null) {
            return;
        }
        //是否可以注入(此处标准为：Controller/Service可以注入)
        Class<?> beanClass = beanWrapperX.getWrapperClass();


        if (!(beanClass.isAnnotationPresent(ControllerX.class) || beanClass.isAnnotationPresent(ServiceX.class))) {
            return;
        }
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {//判断是否加了自动注入注解(此处简化-只考虑Autowired)
            if (!field.isAnnotationPresent(AutowiredX.class)) {
                continue;
            }
            //读取注解中的bean值，如果没有就读取类型值，从ioc容器中获取对应的类信息(如果容器中没有呢？)
            AutowiredX autowiredX = field.getAnnotation(AutowiredX.class);
            String autowriedBeanName = autowiredX.value().trim();
            if ("".equals(autowriedBeanName)) {
                autowriedBeanName = field.getType().getName();
            }

            field.setAccessible(true);//突破private

            try {
                //自动注入(字段赋值)
                field.set(instance, this.factoryBeanInstanceCache.get(autowriedBeanName).getWrapperClass());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private BeanWrapperX instatiateBean(String beanName, BeanDefinitionX beanDefinitionX) {
        //1 拿到实例话的对象的类名
        String className = beanDefinitionX.getBeanClassName();
        //2 反射实例化，得到一个对象
        Object instance = null;
        try {
            //实例化对象-先看容器中有没有
            //此处假设默认是单例
            if (null != singletonContainer.get(beanName)) {
                instance = singletonContainer.get(beanName);
            } else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                singletonContainer.put(beanName, instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //3 将对象封装到BeanWrapper中
        BeanWrapperX beanWrapperX = new BeanWrapperX(instance);

        return beanWrapperX;
    }


    private void doAutowired() {

        super.beanDefinitionMap.entrySet().stream().forEach(entry -> {
            String key = entry.getKey();
            BeanDefinitionX beanDefinitionX = entry.getValue();
            //非延迟加载
            if (!beanDefinitionX.isLazyInit()) {
               try {
                   getBean(key);
               }catch (Exception e){
                   e.printStackTrace();
               }
            }
        });

    }

    private void doRegisterBeanDefinition(List<BeanDefinitionX> beanDefinitionXES) {

        beanDefinitionXES.stream().forEach(beanDefinitionX -> {
            super.beanDefinitionMap.put(beanDefinitionX.getFactoryBeanName(), beanDefinitionX);
        });
    }



}
