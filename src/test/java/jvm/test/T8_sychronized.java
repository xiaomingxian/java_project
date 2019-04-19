package jvm.test;

public class T8_sychronized {

    /**
     * synchronized锁可重入
     *
     * @param args
     */
    public static void main(String[] args) {

        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        r1();
        //    }
        //}).start();
        //    锁中抛出异常测试---如果不抛出异常的话t2不会执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                exT();
            }
        }, "t1").start();

        try {
            Thread.sleep(1000);

        } catch (Exception e) {

        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                exT();
            }
        }, "t2").start();

    }

    public static synchronized void exT() {
        int cou = 0;
        while (true) {

            System.out.println(Thread.currentThread().getName());
            cou++;
            if (cou == 5) {
                //查看正常情况可以注释掉以下代码
                cou = 1 / 0;
            }
        }

    }

    public static synchronized void r1() {
        System.out.println("111111");
        r2();
    }

    public static synchronized void r2() {
        System.out.println("2222");

    }


}
