package spring_source_code.test;

import org.junit.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_source_code.config.MainConfigAop;
import spring_source_code.pojo.Math;

public class T5_AOP1 {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfigAop.class);

    @Test
    public void t1() {
        //不会异常以doAround同时存在 异常捕获不到----
        // 1.因为returning中基本类型无法接受null
        //2.doAround 总会正常返回------返回的是增强后的代理对象(cglib)
        Math math = (Math) applicationContext.getBean("math");
        //环绕通知得执行并且返回返回值--returing才能接收到
        //JoinPoint joinPoint必须写在参数列表的第一位
        //math.caculate(10, 2);
        math.caculate(10, 0);//异常捕获测试
    }

    @Test
    public void yuanli() {
        //@EnableAspectJAutoProxy//开启切面自动代理
        //1.继承关系
        //注入--关键组件---
        AnnotationAwareAspectJAutoProxyCreator s = null;//父类
        AspectJAwareAdvisorAutoProxyCreator ss = null;//父类
        AbstractAdvisorAutoProxyCreator sss = null;//父类
        AbstractAutoProxyCreator ssss = null;//父类
        //实现接口
        SmartInstantiationAwareBeanPostProcessor sx = null;//后置处理器，在bean初始化前后做一些事
        BeanFactoryAware fa = null;//可以传进IOC容器[bean工厂]


        //2.注入IOC容器，注入后置处理器

        //========================== 以上是创建和注册AnnotationAwareAspectJAutoProxyCreator ================================

        //3.在Bean 创建前进行拦截-判断是否是切面
        //由后置处理器创建  BeanPostProcessor.AnnotationAwareAspectJAutoProxyCreator
        //是这种类型的后置处理器：InstantiationAwareBeanPostProcessor  ------->会在所有bean创建之前做拦截  postProcessAfterInstantiation()
        //InstantiationAwareBeanPostProcessor sxx=null;
        //sxx.postProcessAfterInstantiation()

        //  postProcessAfterInstantiation()--->...  判断是否是切面
        //        3.1 判断是否是基础类 实现了 Adcice,PointCut,Advisor,AopInfrastructureBean  等接口
        //        3.2 判断是否加了 @Aspect注解

        //4.创建代理
        //    如果当前Bean 需要增强(有通知方法)
        //    保存到proxyFactory
        //    创建代理对象 JDK的动态代理还是Cglib的有Spring决定[是否实现接口，也可以强制使用Cglib]

        //5.返回代理对象

        //========================== 执行流程 ================================
        //6.目标方法执行
        //    6-1 CglibAopProxy.intercept() 拦截目标方法的执行
        //    6-2 根据proxyFactory 对象获取将要执行的的目标方法的拦截器链
        //          拦截器链：增强器[被包装成Iterceptor]的集合[遍历所有的增强器将其转为Interceptor]
        //                  如果是MethodInterceptor 就直接加进去，如果不是需要用适配器转换以下
        //    6-3 如果没有拦截器链 就执行原始方法
        //    6-4 如果有拦截器链 把需要执行的目标对象，方法，拦截器链等 传入CglibMethodInvocation对象，并调用它的 .proceed()  得到返回值进行处理
        //    6-5 拦截器链的触发过程  .proceed()的执行过程
        //        1. 如果没有拦截器，或者拦截器的索引==拦截器数组长度-1(到了最后一个)就执行原始方法
        //        2. 链式获取每一个拦截器，拦截器执行invoke方法每一个拦截器等待下一个拦截器执行完成返回以后再来执行

    }

    @Test
    public void ttt() {

        int i = 0;

        Advisor d=null;
        int j = ++i;
        System.out.println(j);

    }
}
