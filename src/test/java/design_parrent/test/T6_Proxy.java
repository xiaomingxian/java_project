package design_parrent.test;

import design_parrent.pojo.p6_proxy.Proxy_;
import design_parrent.pojo.p6_proxy.StaticProxed;
import design_parrent.pojo.p6_proxy.StaticProxy;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class T6_Proxy {


    /**
     * 静态代理
     */
    @Test
    public void staticProxy() {

        StaticProxed staticProxed = new StaticProxed();
        Proxy_ staticProxy = new StaticProxy(staticProxed);

        staticProxy.doSomeTh();
    }

    /**
     * 动态代理
     */
    @Test
    public void dTProxy() {

        StaticProxed staticProxed = new StaticProxed();

        Proxy_ obj = (Proxy_) Proxy.newProxyInstance(staticProxed.getClass().getClassLoader(), staticProxed.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("--------前");
                Object invoke = method.invoke(staticProxed, args);
                System.out.println("--------后");
                return invoke;
            }
        });

        obj.doSomeTh();


    }


}
