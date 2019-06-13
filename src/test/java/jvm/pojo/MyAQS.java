package jvm.pojo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyAQS implements Lock {

    MyLock lock = new MyLock();

    @Override
    public void lock() {
        //acquire已经帮助实现了 内部的排队机制和竞争机制
        //模版方法设计模式----具体实现由自己实现
        lock.acquire(1);
        /**
         *  if (!tryAcquire(arg) &&
         *             acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
         *           selfInterrupt();？？？？？？？咩明白
         * ---------------
         * Thread.currentThread().interrupt();
         *
         *  从当前线程去拿锁，拿不到再排队去队列中拿
          */

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
        //模版方法设计模式----具体实现由自己实现
        lock.release(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    //

    //官方建议用内部类实现
    private class MyLock extends AbstractQueuedSynchronizer {
        //acquire已经帮助实现了 内部的排队机制和竞争机制
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;//官方规定传入得是1 其他不管用 unused
            if (compareAndSetState(0, 1)) {
                //设置当前线程独占锁--排他锁
                setExclusiveOwnerThread(Thread.currentThread());
                //返回true说明已经获得了锁
                return true;
            }
            return false;
        }


        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            //如果当前线程不是锁定的状态
            if (!isHeldExclusively()) throw new IllegalStateException();//抛异常只是一种处理方式
            //设置锁定线程为null
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //
        @Override
        protected boolean isHeldExclusively() {
            //判断获得锁的线程是否是当前线程
            return getExclusiveOwnerThread() == Thread.currentThread();
        }
    }


}
