package springx.context;

import org.apache.commons.lang3.StringUtils;
import springx.annotation.AutowiredX;
import springx.annotation.ControllerX;
import springx.annotation.ServiceX;
import springx.aop.AopProxyX;
import springx.aop.CglibDynamicProxyX;
import springx.aop.JdkDynamicAopProxy;
import springx.aop.config.AopConfigX;
import springx.aop.support.AdvisedSupportX;
import springx.beans.BeanFactoryX;
import springx.beans.BeanWrapperX;
import springx.beans.config.BeanDefinitionX;
import springx.beans.config.BeanPostProcessorX;
import springx.beans.support.BeanDefinitionReaderX;
import springx.beans.support.DefaultListableBeanFactoryX;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AnnotationContext...
 * XmlContext...等等
 */
public class ApplicationContextX extends DefaultListableBeanFactoryX implements BeanFactoryX {

    public ApplicationContextX() {
    }

    private BeanDefinitionReaderX beanDefinitionReaderX;

    private String[] locations;


    //单例对象容器(单例IOC容器缓存)
    Map<String, Object> singletonContainer = new ConcurrentHashMap();

    //BeanWrapper容器(通用IOC容器)
    private Map<String, BeanWrapperX> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public ApplicationContextX(String... locations) {
        this.locations = locations;
        refresh();
    }

    /**
     * 根据类型获取
     *
     * @param clazz
     * @return
     */
    public Object getBean(Class<?> clazz) throws Exception {
        String simpleName = clazz.getSimpleName();
        //首字母小写
        String beanName = StringUtils.lowerCase(simpleName.substring(0, 1)) + simpleName.substring(1);
        //也可以在初始化的时候存储一份 key是类明的  [一式两份] 是否浪费空间
        return getBean(beanName);
    }

    @Override
    public Object getBean(String beanName) throws Exception {

        //0 doGetBean
        /**
         * 为什么要先初始化在注入[而不是同时做]：因为要解决循环依赖的问题
         */
        BeanDefinitionX beanDefinitionX = beanDefinitionMap.get(beanName);
        //工厂模式+策略模式[mybatis的sessionFactory实现方式]
        BeanPostProcessorX beanPostProcessorX = new BeanPostProcessorX();

        //前置通知
        beanPostProcessorX.postProcessBeforeInitialization(beanDefinitionX, beanName);

        //1 初始化(仅初始化，不注入属性值)  得提前初始化吧  注入的时候类都没初始化怎么注入
        BeanWrapperX beanWrapperX = instatiateBean(beanName, beanDefinitionMap.get(beanName));

        //后置通知
        beanPostProcessorX.postProcessAfterInitialization(beanWrapperX, beanName);

        //2 将BeanWrapper保存到 wrapper容器中
        factoryBeanInstanceCache.put(beanName, beanWrapperX);


        //3 注入(注入属性值)
        populateBean(beanName, beanWrapperX);



        return factoryBeanInstanceCache.get(beanName).getWrapperInstance();
    }

    @Override
    public void refresh() {
        //1 定位配置文件
        this.beanDefinitionReaderX = new BeanDefinitionReaderX(this.locations);

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

        //if (instance == null) {//此处已经在 doCreateBeanDefinition 将不能实例化的类过滤了(抽象类，接口)
        //    return;
        //}
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
                String simpleName = field.getType().getSimpleName();
                autowriedBeanName = StringUtils.lowerCase(simpleName.substring(0, 1)) + simpleName.substring(1);
            }

            field.setAccessible(true);//突破private

            try {
                //自动注入(字段赋值)
                Object instanceWried = this.factoryBeanInstanceCache.get(autowriedBeanName).getWrapperInstance();
                //注入就一次，没注入的化再进行注入(我自己的做法)
                Object o = field.get(instance);
                if (o==null){
                    System.out.println("-------------------->>>>依赖注入->被注入的类实例：" + instanceWried);
                    field.set(instance, instanceWried);
                }
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
        BeanWrapperX beanWrapperX = null;
        try {
            //实例化对象-先看容器中有没有
            //此处假设默认是单例
            if (null != singletonContainer.get(beanName)) {
                instance = singletonContainer.get(beanName);
            } else {
                Class<?> clazz = Class.forName(className);
                //在这里初始化Aop配置？？？
                AdvisedSupportX config = instantionAopConfig(beanDefinitionX);

                //判断是否符合切面规则
                if (config.pointCutMatch()) {
                    instance = createProxy(config).getProxy();
                } else {
                    instance = clazz.newInstance();
                }
                config.setTarget(instance);
                config.setTargetClass(clazz);
                //3 将对象封装到BeanWrapper中
                beanWrapperX = new BeanWrapperX(instance);
                singletonContainer.put(className, instance);
                factoryBeanInstanceCache.put(beanDefinitionX.getFactoryBeanName(), beanWrapperX);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return beanWrapperX;
    }

    private AopProxyX createProxy(AdvisedSupportX config) {
        Class<?>[] interfaces = config.getTargetClass().getInterfaces();
        if (interfaces.length > 0) {
            return new JdkDynamicAopProxy(config);
        } else {
            return new CglibDynamicProxyX(config);
        }
    }

    private AdvisedSupportX instantionAopConfig(BeanDefinitionX beanDefinitionX) {
        AopConfigX aopConfigX = new AopConfigX();
        aopConfigX.setAspectClass(this.beanDefinitionReaderX.getConfig().getProperty("aspectClass"));
        aopConfigX.setPointCut(this.beanDefinitionReaderX.getConfig().getProperty("pointCut"));
        aopConfigX.setAspectBefore(this.beanDefinitionReaderX.getConfig().getProperty("aspectBefore"));
        aopConfigX.setAspectAfter(this.beanDefinitionReaderX.getConfig().getProperty("aspectAfter"));
        aopConfigX.setAspectAfterThrow(this.beanDefinitionReaderX.getConfig().getProperty("aspectAfterThrow"));
        aopConfigX.setAspectAfterThrowing(this.beanDefinitionReaderX.getConfig().getProperty("aspectAfterThrowing"));
        return new AdvisedSupportX(aopConfigX);
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
            //直接实例化---提前实例化用于依赖注入(并且解决循环依赖[只实例话并不对属性赋值])
            BeanWrapperX beanWrapperX = instatiateBean(beanDefinitionX.getFactoryBeanName(), beanDefinitionX);
            factoryBeanInstanceCache.put(beanDefinitionX.getFactoryBeanName(), beanWrapperX);

        });
    }


    /**
     * 获取容器的key
     */
    public String[] getBeanDefinitionNames() {
        //只返回key--最少知道原则
        return this.beanDefinitionMap.keySet().toArray(new String[this.beanDefinitionMap.size()]);
    }

    /**
     * 获取容器对象数量
     * @return
     */
    public int getBeanDefinitionCount() {
        return this.beanDefinitionMap.size();
    }


    public Properties getConfig() {
        return beanDefinitionReaderX.getConfig();
    }
}
