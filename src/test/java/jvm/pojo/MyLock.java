package jvm.pojo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private volatile int i = 0;//0没有锁--1锁

    @Override
    public void lock() {
        while (i != 0) {
            //    其他线程在用--当前线程等待
            try {
                this.wait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //    其他线程没有使用锁那就自己使用
        i = 1;


    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        i = 0;
        //    唤醒其他线程
        this.notifyAll();

    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
