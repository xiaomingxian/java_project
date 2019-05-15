package interview.t0.mianshi.test;


import lombok.Data;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class T3_ABA {
    /**
     * 原子引用----针对对象的操作
     */
    @Test
    public void t1() {
        AtomicInteger i = null;

        User zs = new User("zs");
        User ls = new User("ls");

        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userAtomicReference.set(zs);

        System.out.println("---->" + userAtomicReference.compareAndSet(zs, ls) + " ");
        System.out.println("---->" + userAtomicReference.compareAndSet(ls, zs) + " ");
        System.out.println("---->" + userAtomicReference.compareAndSet(zs, ls) + " ");

    }


    AtomicInteger atomicInteger = new AtomicInteger(10);

    /**
     * ABA问题模拟
     * 2个线程对它操作 一个快 一个慢
     */
    @Test
    public void t2() {
        new Thread(() -> {
            atomicInteger.compareAndSet(10, 20);
            atomicInteger.compareAndSet(20, 30);
            boolean b = atomicInteger.compareAndSet(30, 10);
            System.out.println(Thread.currentThread().getName() + "--进行ABA操作，是否成功：" + b);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            boolean b = atomicInteger.compareAndSet(10, 20);
            System.out.println(Thread.currentThread().getName() + "--速度慢的线程对数值进行操作,是否成功：" + b + " 操作结果：" + atomicInteger.get());
        }).start();


        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
        }).start();


        //为了不让主线程提前结束
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }
    }

    AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100, 0);

    /**
     * 解决方案1 版本号
     */
    @Test
    public void stamp() {
        //基本测试
        //baseTest();

        new Thread(() -> {
            boolean b = atomicStampedReference.compareAndSet(100, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("-->是否成功：" + b + " 版本号：" + atomicStampedReference.getStamp());
            b = atomicStampedReference.compareAndSet(1, 3, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("-->是否成功：" + b + " 版本号：" + atomicStampedReference.getStamp());

            b = atomicStampedReference.compareAndSet(3, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            System.out.println("-->是否成功：" + b + " 版本号：" + atomicStampedReference.getStamp());
            System.out.println("=====时间短的线程执行完毕");
        }).start();

        new Thread(() -> {
            try {
                int stamp = atomicStampedReference.getStamp();
                Thread.sleep(1000);
                boolean b = atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1);
                System.out.println("是否成功：" + b + " 版本号：" + atomicStampedReference.getStamp() + " 实际值：" + atomicStampedReference.getReference());
            } catch (Exception e) {
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }
    }

    /**
     * 解决方式二 时间戳
     * boolean标记是否被改过   并不能解决ABA问题 只是会降低几率
     * <p>
     * <p>
     * AtomicMarkableReference则是将一个boolean值作是否有更改的标记，本质就是它的版本号只有两个，true和false，
     * 修改的时候在这两个版本号之间来回切换，这样做并不能解决ABA的问题，只是会降低ABA问题发生的几率而已；
     */
    AtomicMarkableReference atomicMarkableReference = new AtomicMarkableReference(100, false);

    @Test
    public void mark() {


        new Thread(() -> {
            atomicMarkableReference.compareAndSet(100, 1, false, true);
            atomicMarkableReference.compareAndSet(1, 12, true, true);
            atomicMarkableReference.compareAndSet(12, 100, true, true);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(1000);
                boolean b = atomicMarkableReference.compareAndSet(100, 100, false, true);//改失败
                //boolean b = atomicMarkableReference.compareAndSet(100, 100, false, true);//改成功
                System.out.println("--->是否成功：" + b);
            } catch (Exception e) {
            }
        }).start();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
        }


    }

    private void baseTest() {
        int stamp = atomicStampedReference.getStamp();
        Object reference = atomicStampedReference.getReference();
        System.out.println(reference + "---- time:" + stamp);
        atomicStampedReference.compareAndSet(100, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        stamp = atomicStampedReference.getStamp();
        reference = atomicStampedReference.getReference();
        System.out.println(reference + "---- time:" + stamp);
    }


}

@Data
class User {
    String name;

    public User(String name) {
        this.name = name;
    }

}
