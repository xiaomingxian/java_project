package spring_source_code.config;


import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        ConfigurableListableBeanFactory beanFactory = conditionContext.getBeanFactory();
        System.out.println("----->beanFactory" + beanFactory);

        ClassLoader classLoader = conditionContext.getClassLoader();
        System.out.println("----->classLoader:" + classLoader);

        BeanDefinitionRegistry registry = conditionContext.getRegistry();
        System.out.println("----->registry:" + registry);

        String property = conditionContext.getEnvironment().getProperty("os.name");
        if (property.contains("Mac")) return true;

        return false;
    }
}
