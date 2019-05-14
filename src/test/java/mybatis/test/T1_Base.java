package mybatis.test;

import mybatis.aop.Select;
import mybatis.config.MyBatisConfig;
import mybatis.mapper.PersonMapper;
import mybatis.pojo.PersonService;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

public class T1_Base {


    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MyBatisConfig.class);

    @Test
    public void t1() {
        SqlSessionFactoryBean sqlSession = applicationContext.getBean(SqlSessionFactoryBean.class);
        PersonService bean = applicationContext.getBean(PersonService.class);

        bean.select();

        //System.out.println(sqlSession);

    }

    /**
     * 模拟aop产生Mapper 的代理类
     */
    @Test
    public void proxy() {
        PersonMapper o = (PersonMapper) Proxy.newProxyInstance(PersonMapper.class.getClassLoader(), new Class[]{PersonMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //这里可以通过 包名+类名+方法名 去全局的MapperedStatment中去寻找 包装了SQL的对象---->下一个测试方法可以产生全局的MapperedStatment
                System.out.println("----产生的代理类进行方法执行");
                //Object invoke = method.invoke(args);
                return null;
            }
        });

        o.select1();
    }

    /**
     * 注解信息获取
     * 模拟MappedStatment初始化
     */
    @Test
    public void t2() throws Exception {
        HashMap<String, String> map = new HashMap<>();

        Class<?> aClass = Class.forName("mybatis.mapper.PersonMapper");

        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.isAnnotationPresent(Select.class)) {
                Select annotation = method.getAnnotation(Select.class);
                System.out.println(annotation.value());
                String key = method.getDeclaringClass().getName() + "." + method.getName();
                map.put(key, annotation.value());
            }
        }

        map.entrySet().stream().forEach(en -> System.out.println("key:" + en.getKey() + "--value:" + en.getValue()));


    }

    @Test
    public void page(){
        RowBounds rowBounds=null;

    }

}
