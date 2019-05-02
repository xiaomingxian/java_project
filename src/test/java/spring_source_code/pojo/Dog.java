package spring_source_code.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Dog implements BeanPostProcessor {

    public Dog() {
        System.out.println("----------->Dog 构造方式创建对象");
    }

    @PostConstruct
    public void init() {
        System.out.println("----------->Dog @PostConstruct");

    }

    @PreDestroy
    public void preDestory() {
        System.out.println("----------->Dog @PreDestroy");

    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("---------->Dog 初始化之后进行,类信息：" + o + ",类名：" + s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("---------->Dog 初始化之前进行,类信息：" + o + ",类名：" + s);

        return o;
    }
}
