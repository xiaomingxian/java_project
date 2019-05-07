package spring_source_code.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.stereotype.Component;
import pojo.Person;
import pojo.User;

@Component
public class MyBeanDefinationPostProcessor implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("=------------------->注册方法执行,bean定义数量：" + beanDefinitionRegistry.getBeanDefinitionCount());
        System.out.println("======---->自定义注入bean定义信息--2种方式");
        RootBeanDefinition person = new RootBeanDefinition(Person.class);
        beanDefinitionRegistry.registerBeanDefinition("person--mine", person);
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(User.class);
        AbstractBeanDefinition user = beanDefinitionBuilder.getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("user--mine", user);
    }

    /**
     * 这条是beanFactoryPostProcessor的方法
     *
     * @param configurableListableBeanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("=------------------->beanFactory的后置处理器执行法执行：bean数量：" + configurableListableBeanFactory.getBeanDefinitionCount());
        System.out.println("=------------------->beanFactory的后置处理数量：" + configurableListableBeanFactory.getBeanPostProcessorCount());
    }

}
