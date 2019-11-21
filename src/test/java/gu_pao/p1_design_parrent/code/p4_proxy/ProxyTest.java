package gu_pao.p1_design_parrent.code.p4_proxy;

import gu_pao.p1_design_parrent.code.p4_proxy.pojo.*;
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
        dynamicProxyJDk();
    }

    private static void dynamicProxyJDk() {

        //被代理对象
        Son son = new Son();

        //这里是匿名实现方式 也可以抽出来写 (生成的代理类与被增强类同级 所以要用接口接收)
        Person sonProxy = (Person) Proxy.newProxyInstance(Son.class.getClassLoader(), Son.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("前置增强");
                Object invoke = method.invoke(son, args);
                System.out.println("后置增强");

                return invoke;
            }
        });


        //产生的代理类 $Proxy0@577 #@后指的是地址值
        //将代理类从内存中读出来写进磁盘 反编译看看
        //继承被代理类实现接口 public final class $Proxy0 extends Proxy implements Person
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
