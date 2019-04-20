package jvm.test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class T9_VolatileTest {
    // 此处如果用static 与volatile效果相同
    static Boolean flag = true;
    volatile Boolean f = true;

    volatile int count = 0;

    //原子性操作
    AtomicInteger cou = new AtomicInteger(0);


    public static void main(String[] args) {
        ////1.静态变量也称为类变量，属于类对象所有，位于方法区，为所有对象共享，共享一份内存，一旦值被修改，则其他对象均对修改可见，故线程非安全。
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        while (flag) {
        //            System.out.println("----111111111------");
        //        }
        //    }
        //}).start();
        //
        //try {
        //    Thread.sleep(1000);
        //} catch (Exception e) {
        //}
        //
        //flag = false;
        //-------------------------------------------------------------------------------------
        //// 2.   volatoile测试
        //T9_VolatileTest t9_volatileTest = new T9_VolatileTest();
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        t9_volatileTest.m();
        //    }
        //}).start();
        //try {
        //    Thread.sleep(1000);
        //} catch (Exception e) {
        //}
        //
        //t9_volatileTest.f = false;
        //-------------------------------------------------------------------------------------
        ////3.线程安全问题，volatile 不具备原子性
        //T9_VolatileTest t9_volatileTest = new T9_VolatileTest();
        //ArrayList<Thread> threads = new ArrayList<>();
        //for (int i = 0; i < 10; i++) {
        //    Thread thread = new Thread(new Runnable() {
        //        @Override
        //        public void run() {
        //            t9_volatileTest.v();
        //        }
        //    }, "t" + i);
        //    threads.add(thread);
        //}
        //for (Thread t : threads) {
        //        t.start();
        //}
        ////Thread中，join（）方法的作用是调用线程等待该线程完成后，才能继续用下运行。
        //for (Thread t : threads) {
        //    try {
        //        t.join();
        //    } catch (Exception e) {
        //    }
        //}
        ////理论上 count  的值为 线程数*循环数
        ////System.out.println("--->变量最终值：" + t9_volatileTest.count);
        ////原子性操作--系统相当底层实现
        //System.out.println("--->变量最终值：" + t9_volatileTest.cou);

        //    改变这种状况 -synchronized 得有 join 才能实现

        //---------------------------------
        // 2.  atomic是否保证可见性--->是
        T9_VolatileTest t9_volatileTest = new T9_VolatileTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                t9_volatileTest.at();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        t9_volatileTest.cou = new AtomicInteger(1);

    }

    //synchronized
    public  void v() {
        for (int i = 0; i < 1000; i++) {
            //count++;
            if (cou.get()<1000)
                cou.incrementAndGet();
        }
    }

    public void m() {
        System.out.println("----start");
        while (f) {
            //入股里面有内容 eg:输出，sleep之类的 线程也会去主内存中去读取内容
            //此处模拟应注释以下内容
            //System.out.println("----111111111------"+f);
        }
        System.out.println("----end");
    }
    public void at() {
        System.out.println("----start");
        while (cou.get()==0) {
            //入股里面有内容 eg:输出，sleep之类的 线程也会去主内存中去读取内容
            //此处模拟应注释以下内容
            //System.out.println("----111111111------"+f);
        }
        System.out.println("----end");
    }
}
