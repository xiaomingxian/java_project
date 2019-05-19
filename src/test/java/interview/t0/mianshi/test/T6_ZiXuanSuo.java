package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class T6_ZiXuanSuo {

    /**
     * 场景：一个线程  拿到锁 执行代码  一段事件后释放锁
     * 另一个线程 发现锁 锁着 于是循环的查看锁状态  知道锁释放才能进来
     */


    /**
     * 原子引用类型的初始值是null
     */
    AtomicReference<Thread> atomicReference = new AtomicReference();

    public void lock(Thread thread) {
        System.out.println(thread.getName() + " 加锁");
        //如果比较并赋值不成功---->就循环(自旋)直到 成功(另一个线程释放锁)
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(thread.getName() + "--在自旋");
        }
        System.out.println(thread.getName() + "---执行代码");
    }

    public void unLock(Thread thread) {
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "  解锁");
    }


    @Test
    public void t1() throws Exception {
        T6_ZiXuanSuo t = new T6_ZiXuanSuo();

        Thread t1 = new Thread("t1");
        Thread t2 = new Thread("t2");


        new Thread(() -> {
            //获得锁-
            t.lock(t1);
            //得不到锁
            t.lock(t2);
        }).start();

        //使得自旋现象显现
        Thread.sleep(10);

        //解锁--终止自旋
        new Thread(() -> {
            t.unLock(t1);


        }).start();
        //t1解开锁--t2获得锁执行代码
        Thread.sleep(50);

        new Thread(() -> {

            t.unLock(t2);


        }).start();


    }

}
