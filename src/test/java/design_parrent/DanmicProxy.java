package design_parrent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DanmicProxy {

    public static void main(String[] args) {
        Son s = new Son();
        Father f_proxy = (Father) Proxy.newProxyInstance(Son.class.getClassLoader(), Son.class.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("-----方法前");
                Object invoke = method.invoke(s, args);
                System.out.println("-----方法后");


                return invoke;
            }
        });
        f_proxy.say();
    }
}


interface Father {

    void say();
}

class Son implements Father {

    @Override
    public void say() {
        System.out.println("--------son say --------");
    }
}