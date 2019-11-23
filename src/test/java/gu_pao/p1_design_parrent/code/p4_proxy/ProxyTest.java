package gu_pao.p1_design_parrent.code.p4_proxy;

import gu_pao.p1_design_parrent.code.p4_proxy.cglib.CglibProxy;
import gu_pao.p1_design_parrent.code.p4_proxy.cglib.Proxyed;
import gu_pao.p1_design_parrent.code.p4_proxy.myproxy.MyClassLoader;
import gu_pao.p1_design_parrent.code.p4_proxy.myproxy.MyInvocationHandler;
import gu_pao.p1_design_parrent.code.p4_proxy.myproxy.MyProxy;
import gu_pao.p1_design_parrent.code.p4_proxy.pojo.*;
import org.springframework.aop.framework.AopProxy;
import org.springframework.cglib.core.DebuggingClassWriter;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

public class ProxyTest {

    public static void main(String[] args) {
        //1 静态代理 代理类持有被代理类的引用 是实现增强
        //staticProxy();

        //1.1 静态代理数据源切换
        //dataSourceChange();
        //1.2 SpringDynamicDataSource[spring中的动态数据源接口简单demo]

        //2.1 JDK的动态代理
        //dynamicProxyJDk();


        //2.2 自定义动态代理(仿jdk) jdk是动态生成类在
        //mySelfDynamic();


        //3 cglib代理(不能代理final修饰的类)
        //cglibProxy();


        //jdk与cglib代理区别
        //cglib: asm直接生成class文件(不需要反射) ;jdk:反射
        //执行效率cglib>jdk 1.8之后[jdk>cglib(官方的更新迭代速度慢)]但是jdk批量创建效率还是低于cglib
        //https://blog.csdn.net/yhl_jxy/article/details/80635012


        //spring中代理模式的应用
        springProxy();

    }

    private static void springProxy() {

        AopProxy aopProxy = null;//查看类图
        //没有配置且又接口用jdk
        //没有接口用cglib
        //可强制配置成cglib

    }

    private static void cglibProxy() {

        try {

            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,
                    "/Users/xxm/develop/workspace/learn/src/test/java/gu_pao/p1_design_parrent/code/p4_proxy/cglib");

            CglibProxy cglibProxy = new CglibProxy();
            Proxyed instance = (Proxyed) cglibProxy.getInstance(Proxyed.class);
            instance.say();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * jdk动态代理为什么要实现共同接口(因为基于接口寻找方法属性，基于invocationHandler进行调用(传参需要方法相关内容))
     *
     *
     * https://blog.csdn.net/u011976388/article/details/80315850
     */
    private static void mySelfDynamic() {

        Son son = new Son();
        MyClassLoader myClassLoader = new MyClassLoader();
        Person person = (Person) MyProxy.newProxyInstance(myClassLoader, Son.class.getInterfaces(), new MyInvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("--->前置增强");
                method.invoke(son,args);
                System.out.println("--->后置增强");

                return null;
            }
        });

        person.findLove();



    }

    private static void dynamicProxyJDk() {

        //被代理对象
        Son son = new Son();

        //这里是匿名实现方式 也可以抽出来写 (生成的代理类与被增强类同级 所以要用接口接收)
        Person sonProxy = (Person) Proxy.newProxyInstance(Son.class.getClassLoader(), Son.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("前置增强");
                System.out.println("--->>>>" + args);
                Object invoke = method.invoke(son, args);
                System.out.println("后置增强");

                return invoke;
            }
        });


        //产生的代理类 $Proxy0@577 #@后指的是地址值
        //将代理类从内存中读出来写进磁盘 反编译看看
        //继承Proxy实现接口 public final class $Proxy0 extends Proxy implements Person
        //public final Person findLove() throws  {
        //    try {
        //        return (Person)super.h.invoke(this, m3, (Object[])null);//super.h指的是实现了InvocationHandler的类(此处是匿名方式)，调用增强后的方法
        //    } catch (RuntimeException | Error var2) {
        //        throw var2;
        //    } catch (Throwable var3) {
        //        throw new UndeclaredThrowableException(var3);
        //    }
        //}
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Person.class});
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("proxy0.class");
            fileOutputStream.write(bytes);
            fileOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        sonProxy.findLove();


    }

    /**
     * * https://blog.csdn.net/weixin_44402359/article/details/94434819
     */
    private static void dataSourceChange() {
        Order order = new Order();
        order.setName("订单1");
        order.setCreateTime(new Date());

        //被代理类
        OrderServiceImpl orderService = new OrderServiceImpl();
        //代理类
        OrderServiceProxy orderServiceProxy = new OrderServiceProxy(orderService);
        orderServiceProxy.createOrder(order);

    }


    /**
     * eg：对数据库进行分库分表
     * <p>
     * https://blog.csdn.net/weixin_44402359/article/details/94434819
     */
    private static void staticProxy() {
        Father father = new Father(new Son());
        father.findLove();
    }
}
