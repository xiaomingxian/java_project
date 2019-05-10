package mybatis.test;

import mybatis.config.MyBatisConfig;
import mybatis.pojo.PersonService;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class T1_Base {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);

    @Test
    public void t1() {
        SqlSessionFactoryBean sqlSession = applicationContext.getBean(SqlSessionFactoryBean.class);
        PersonService bean = applicationContext.getBean(PersonService.class);

        bean.select();

        //System.out.println(sqlSession);

    }


}
