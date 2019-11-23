package gu_pao.p1_design_parrent.code.p4_proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {


    public Object getInstance(Class<?> clazz) {
        //相当于JDK的Proxy
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        //传入自己-为了调用intercept
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {


        System.out.println("--->cglib前置增强");
        //调用父类的方法
        methodProxy.invokeSuper(o, objects);

        System.out.println("--->cglib后置增强");
        return null;
    }
}
