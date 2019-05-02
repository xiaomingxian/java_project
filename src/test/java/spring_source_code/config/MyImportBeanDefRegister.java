package spring_source_code.config;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import pojo.excelpojo.Teacher;

public class MyImportBeanDefRegister implements ImportBeanDefinitionRegistrar {
    /**
     * @param annotationMetadata     当前类的注解信息
     * @param beanDefinitionRegistry bean定义的注册类
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {

        boolean mac = beanDefinitionRegistry.containsBeanDefinition("mac");
        System.out.println("------>判断bean是否存在：" + mac);

        beanDefinitionRegistry.registerBeanDefinition("teacher", new RootBeanDefinition(Teacher.class));


    }
}
