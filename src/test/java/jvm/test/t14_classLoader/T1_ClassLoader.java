package jvm.test.t14_classLoader;

import org.junit.Test;

public class T1_ClassLoader {
    /**
     * 类初始化
     * 主动使用与被动使用
     * static{...}类初始化时调用  ---仅仅标记初始化
     * 类的初始化只执行一次？？？？？？？
     */
    @Test
    public void t1() {
        System.out.println(Son.str);//仅仅父类初始化----类初始化追踪，即使没有对子类初始化也完成了子类的加载 -XX:+TraceClassLoading
        //System.out.println("-------------- 子类初始化父类先初始化 --------------------");
        //System.out.println(Son.str2);
    }
}


class Parent {
    public static String str = "hello world";

    static {
        System.out.println("Parent 初始化");
    }
}

class Son extends Parent {
    public static String str2 = "i am Son";

    static {
        System.out.println("Son 初始化");
    }

}