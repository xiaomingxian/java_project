package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class T7_ReadWriteLock {


    @Test
    public void t1() {

        MyCache cache = new MyCache();
        //5个线程读  5个线程写
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                final int x = i;
                new Thread(() -> {

                    cache.put(x + "", x + "");
                }, "线程：" + x).start();

            }

        }).start();

        try {
            Thread.sleep(300);
        } catch (Exception e) {
        }

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                final int x = i;

                new Thread(() -> {
                    cache.get(x + "");
                }, "线程：" + i).start();

            }

        }).start();
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }
    }
}

class MyCache {

    //保证数据的可见性
    volatile Map map = new HashMap();

    //读写锁
    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    /**
     * 存入缓存
     *
     * @param k
     * @param v
     */
    public void put(String k, String v) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "---开始写入");

        try {

            Thread.sleep(10);
        } catch (Exception e) {
        }
        map.put(k, v);

        System.out.println(Thread.currentThread().getName() + "---写入完成");
        lock.writeLock().unlock();
    }

    /**
     * 获取缓存
     *
     * @param k
     */
    public void get(String k) {
        //读有没有必要加锁
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "---正在读取");

        try {

            Thread.sleep(10);
        } catch (Exception e) {
        }
        Object v = map.get(k);
        System.out.println(Thread.currentThread().getName() + "---读取完成：" + v);

        lock.readLock().unlock();
    }


    /**
     * 清理缓存--略
     */
    public void clear() {
    }
}