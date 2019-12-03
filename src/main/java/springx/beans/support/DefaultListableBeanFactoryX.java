package springx.beans.support;

import springx.beans.config.BeanDefinitionX;
import springx.context.support.AbstractApplicationContextX;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * AbstractApplicationContextX的默认实现
 * 初始化Bean
 */
public class DefaultListableBeanFactoryX extends AbstractApplicationContextX {


    /**
     * IOC容器
     */
    private final Map<String, BeanDefinitionX> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinitionX>();


    @Override
    public void refresh() {

    }
}
