package spring_source_code.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class MyAware implements BeanNameAware, ApplicationContextAware, EmbeddedValueResolverAware {
    @Override
    public void setBeanName(String s) {
        System.out.println("----->bean name:" + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("----->IOC 容器：" + applicationContext);

    }

    //替换环境变量？？？
    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        String s = stringValueResolver.resolveStringValue("系统名称${os.name},--#{10*3}");
        System.out.println(s);
    }
}
