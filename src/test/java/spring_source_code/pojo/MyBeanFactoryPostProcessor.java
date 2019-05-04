package spring_source_code.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@ComponentScan({"spring_source_code.pojo"})
@Configuration
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String[] beanDefinitionNames = configurableListableBeanFactory.getBeanDefinitionNames();

        System.out.println("--------------->beanFactoryPostProcessor,当前bean个数：" + beanDefinitionNames.length);
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        System.out.println("======================================");

    }
}
