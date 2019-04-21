package jvm.test.T12_juc;

public class T1_single {
    /**
     * 单例模式
     * 1.饿汉
     * 2.懒汉 线程安全 方法上加锁
     * 3.懒汉 双重锁
     * 4.内部类不加锁 懒加载
     */

    private static class Inner {
        public static T1_single t = new T1_single();
    }

    public static T1_single getSing() {
        return Inner.t;
    }

}
