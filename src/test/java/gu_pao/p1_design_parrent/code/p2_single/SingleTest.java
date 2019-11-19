package gu_pao.p1_design_parrent.code.p2_single;

import gu_pao.p1_design_parrent.code.p2_single.pojo.Lazy;
import gu_pao.p1_design_parrent.code.p2_single.pojo.LazyDoubleCheck;
import gu_pao.p1_design_parrent.code.p2_single.pojo.StaticInnerClass;

public class SingleTest {

    public static void main(String[] args) {
        //1 线程不安全方式
        //        threadUnSafe();
        //2 线程安全方式
        //threadSafe();

        //3 单层检查 线程不安全
        //lazyDoubleCheckThreadUnSafe();
        //4 双重检查 线程安全
        //lazyDoubleCheckThreadSafe();

        //5 静态内部类
        staticInnerClass();
    }

    private static void staticInnerClass() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(StaticInnerClass.getInstance());
                }
            }).start();
        }


    }


    private static void lazyDoubleCheckThreadUnSafe() {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(LazyDoubleCheck.getInstanceThreadUnSafeOneCheck());
                }
            }).start();
        }
    }

    private static void lazyDoubleCheckThreadSafe() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(LazyDoubleCheck.getInstanceThreadSafe());
                }
            }).start();
        }
    }

    private static void threadSafe() {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Lazy.getInstanceThreadSafe());
                }
            }).start();
        }


    }

    private static void threadUnSafe() {

        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Lazy.getInstance());
                }
            }).start();
        }
    }
}
