package spring_source_code.test;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_source_code.config.MainConfigProfile;

import javax.sql.DataSource;
import java.util.Arrays;

public class T4_Profile {

    //AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigProfile.class);
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
    //  有参构造
    //  有参构造加载容器最后有个refresh 再设置参数已经无用
    //    public AnnotationConfigApplicationContext(Class... annotatedClasses) {
    //        this();
    //        this.register(annotatedClasses);
    //        this.refresh();
    //    }

    /**
     * 指定运行环境
     * 1.设置虚拟机参数：-Dspring.profiles.active=test
     * 2.代码方式[注意使用无参构造]applicationContext.getEnvironment().setActiveProfiles("dev","pro");
     */
    @Test
    public void data() {
        //这几部是将 有参构造拆分了
        applicationContext.getEnvironment().setActiveProfiles("dev", "pro");
        applicationContext.register(MainConfigProfile.class);
        applicationContext.refresh();

        String[] beanNamesForType = applicationContext.getBeanNamesForType(DataSource.class);
        Arrays.stream(beanNamesForType).forEach(System.out::println);
    }
}
