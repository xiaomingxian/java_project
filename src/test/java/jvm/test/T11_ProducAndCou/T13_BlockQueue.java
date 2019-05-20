package jvm.test.T11_ProducAndCou;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class T13_BlockQueue {

    //可以用多种阻塞队列来实现
    BlockingQueue queue = new LinkedBlockingQueue(10);


    public void add(Object o) {
        try {
            //消息满了会阻塞
            queue.put(o);
            Thread.sleep(1000);
        } catch (Exception e) {
        }

    }

    public Object get() {
        Object take = null;
        try {
            take = queue.take();
        } catch (Exception e) {
        }
        return take;
    }

    public static void main(String[] args) {

        T13_BlockQueue t = new T13_BlockQueue();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    t.add("线程1添加：" + i);
                    System.out.println("线程1添加");
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    t.add("线程2添加：" + i);
                    System.out.println("线程2添加");
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("消费者1：" + t.get());
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("消费者2：" + t.get());
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("消费者3：" + t.get());
                }
            }
        }).start();

    }

}
