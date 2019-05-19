package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class T5_ChongRuSuo {


    @Test
    public void t1() {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMsg();
        }).start();

        try {

            Thread.sleep(1);
        } catch (Exception e) {
        }

        new Thread(phone).start();
        new Thread(phone).start();

    }

}

class Phone implements Runnable {

    public synchronized void sendMsg() {
        System.out.println(Thread.currentThread().getName() + "----------->sendMsg");
        callPhone();
    }

    public synchronized void callPhone() {
        System.out.println(Thread.currentThread().getName() + "==========>callphone");
    }

    ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {

        l1();
    }

    /**
     * 加多个锁---此处为阿里面试题
     * 加锁释放锁得成对出现
     */
    public void l1() {
        lock.lock();
        lock.lock();
        System.out.println("------->1111");
        l2();
        lock.unlock();
        lock.unlock();
    }

    private void l2() {
        lock.lock();
        System.out.println("------->2222");
        lock.unlock();
    }
}