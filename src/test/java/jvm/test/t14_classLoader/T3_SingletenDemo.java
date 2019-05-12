package jvm.test.t14_classLoader;

import org.junit.Test;

/**
 * 类的初始化是有顺序的自上而下
 */
public class T3_SingletenDemo {
    /**
     * 普通单例
     */
    @Test
    public void t1() {
        C1 instance = C1.getInstance();
        System.out.println(instance.a);
        System.out.println(instance.b);
    }

    /**
     * 调整顺序1
     */
    @Test
    public void t2() {
        C2 instance = C2.getInstance();
        System.out.println(instance.a);
        System.out.println(instance.b);
    } /**
     * 调整顺序2
     */
    @Test
    public void t3() {
        C3 instance = C3.getInstance();
        System.out.println(instance.a);
        System.out.println(instance.b);
    }
}

class C1 {
    //2无初始化--准备阶段的值都是0
    public static int a;
    public static int b;
    //3类初始化---准备阶段的值null
    private static C1 c1 = new C1();

    //4实际初始化
    private C1() {
        a++;
        b++;
    }

    //1  调用类的静态方法[主动使用]
    public static C1 getInstance() {
        return c1;
    }
}

class C2 {
    //2无初始化--准备阶段的值都是0
    public static int a;
    //3类初始化---准备阶段的值null
    private static C2 c1 = new C2();

    //4实际初始化
    private C2() {
        a++;
        b++;
    }

    //5 按顺序初始化--赋真正值
    public static int b = 0;
    //1  调用类的静态方法[主动使用]

    public static C2 getInstance() {
        return c1;
    }
}


class C3{
    //2无初始化--准备阶段的值都是0
    public static int a=1;
    //3类初始化---准备阶段的值null
    private static C3 c1 = new C3();

    //4实际初始化
    private C3() {
        a++;
        b++;
    }

    //5 按顺序初始化--赋真正值
    public static int b = 0;
    //1  调用类的静态方法[主动使用]

    public static C3 getInstance() {
        return c1;
    }
}