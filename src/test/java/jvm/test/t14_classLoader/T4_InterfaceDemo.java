package jvm.test.t14_classLoader;

import org.junit.Test;

import java.util.UUID;

public class T4_InterfaceDemo {

    @Test
    public void t1() {
        //System.out.println(B.str);
        //System.out.println(B.st2);//调用A的常量[在编译器就可以确定的常量]--不会导致接口初始化
        //System.out.println(B.st);//调用A
        System.out.println(C.s);//子类初始化--不会导致实现的接口初始化

    }
}


interface A {
    String st = UUID.randomUUID().toString();
    String st2 = "---";

    Thread t = new Thread() {
        {
            System.out.println("=---A 初始化");
        }
    };
}

interface B extends A {

    String str = UUID.randomUUID().toString();

}

class C implements A {
    static String s = "sss";

    static {
        System.out.println("--子类初始化---");
    }
}
