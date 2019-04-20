package jvm.test.T11_ProducAndCou;


import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class T12_HandLock {

    LinkedList list = new LinkedList<>();

    static int MAXSIZE = 10;

    ReentrantLock lock = new ReentrantLock();
    Condition p = lock.newCondition();
    Condition c = lock.newCondition();

    public void put(Object o) {
        try {
            Thread.sleep(1000);
        }catch (Exception e){}
        lock.lock();
        while (list.size() == MAXSIZE) {
            try {
                p.await();
            } catch (Exception e) {
            }
        }
        list.add(o);
        //    唤醒所有消费者
        c.signalAll();
        //    释放锁
        lock.unlock();
    }

    public Object get() {
        try {
            Thread.sleep(1000);
        }catch (Exception e){}
        lock.lock();
        while (list.size() == 0) {
            try {
                c.await();
            } catch (Exception e) {
            }
        }
        //    生产者
        p.signalAll();
        //    释放锁
        Object o = list.removeFirst();//放在锁内
        lock.unlock();
        return o;

    }


    public static void main(String[] args) {
        T12_HandLock t = new T12_HandLock();
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    t.put("---p1---");
                    System.out.println("-----p1生产");

                }

            }
        });
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    t.put("---p2---");
                    System.out.println("-----p2生产");
                }

            }
        });
        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("c1:"+ t.get());
                }

            }
        });
        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("c2:"+ t.get());
                }
            }
        });

        Thread c3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("c3:"+ t.get());
                }
            }
        });


        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();

    }


}
