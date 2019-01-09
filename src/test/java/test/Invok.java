package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Invok {

    public static void main(String[] args) {

        //被代理的类
        final B b = new B();

        A o = (A) Proxy.newProxyInstance(B.class.getClassLoader(), B.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("--------before...");
                Object invoke = method.invoke(b, args);
                System.out.println("--------after...");

                return invoke;
            }
        });

        o.getNum();


    }
}


interface A {
    void getNum();

}

class B implements A {
    @Override
    public void getNum() {
        System.out.println("-----B-------");
    }
}