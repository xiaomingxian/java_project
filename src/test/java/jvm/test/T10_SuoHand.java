package jvm.test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 手工锁
 * 可替代sychronized
 * 需要手动解锁
 * 有公平锁和非公平锁  sychronized是非公平锁
 */
public class T10_SuoHand {

    //ReentrantLock lock = new ReentrantLock();
    //公平锁--谁等的时间长就谁执行
    ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        //----------------------------1.sychronized 功能模拟
        //T10_SuoHand t = new T10_SuoHand();
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        t.lock.lock();
        //        for (int i = 0; i < 10; i++) {
        //
        //            System.out.println("-->线程1");
        //        }
        //        t.lock.unlock();
        //    }
        //}).start();
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        t.lock.lock();
        //        System.out.println("-->线程2");
        //        t.lock.unlock();
        //    }
        //}).start();
        //----------------------------2.trylock

        //T10_SuoHand t = new T10_SuoHand();
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        t.lock.lock();
        //        for (int i = 0; i < 10; i++) {
        //
        //            System.out.println("-->线程1");
        //        }
        //        t.lock.unlock();
        //    }
        //}).start();
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        //尝试连接
        //        //boolean b = t.lock.tryLock();
        //        try {
        //            //带时长尝试连接
        //            //boolean b = t.lock.tryLock(2, TimeUnit.SECONDS);
        //            boolean b = t.lock.tryLock(2, TimeUnit.SECONDS);
        //            System.out.println("是否连接成功："+b);
        //        }catch (Exception e){}
        //        System.out.println("-->线程2");
        //        t.lock.unlock();
        //    }
        //}).start();
        //----------------------------3.可被打断
        //boolean flag = true;
        //T10_SuoHand t = new T10_SuoHand();
        //Thread t1 = new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("线程1启动");
        //        try {
        //            t.lock.lockInterruptibly();
        //            while (flag) {
        //                System.out.println("-----永远循环");
        //
        //                Thread.sleep(1000);
        //
        //            }
        //            t.lock.unlock();
        //        } catch (Exception e) {
        //        }
        //    }
        //});
        //Thread t2 = new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        System.out.println("线程2启动");
        //        //可以打断
        //        try {
        //            t.lock.lockInterruptibly();
        //            Thread.sleep(2000);
        //        } catch (Exception e) {
        //        }
        //
        //
        //    }
        //});
        //
        //t1.start();
        //t2.start();
        //try {
        //    Thread.sleep(5000);
        //} catch (Exception e) {
        //}
        //t2.interrupt();
        //t1.interrupt();
        //System.out.println("打断");
        //----------------------------3.公平锁test
        T10_SuoHand t = new T10_SuoHand();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<10;i++){
                    t.lock.lock();
                    System.out.println("线程1");
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){}
                    t.lock.unlock();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i=0;i<10;i++){
                    t.lock.lock();
                    try {
                        Thread.sleep(100);
                    }catch (Exception e){}
                    System.out.println("线程2");
                    t.lock.unlock();
                }

            }
        }).start();

    }


}
