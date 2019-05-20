package interview.t0.mianshi.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class T13_ProAndConsumer {

    /**
     * 略
     */


    BlockingQueue queue = new ArrayBlockingQueue(10);

    volatile AtomicInteger i = new AtomicInteger();

    public void pro() {
        try {
            boolean offer = queue.offer(i.incrementAndGet(), 100, TimeUnit.MILLISECONDS);
            System.out.println("生产：" + offer);
            Thread.sleep(700);
        } catch (Exception e) {
        }
    }

    public void consumer() {

        try {

            Object poll = queue.poll(100, TimeUnit.MILLISECONDS);
            //直到取出值
            while (poll == null) {
                poll = queue.poll(100, TimeUnit.MILLISECONDS);
            }

            System.out.println(poll);
        } catch (Exception e) {
        }

    }

    public static void main(String[] args) {
        T13_ProAndConsumer t = new T13_ProAndConsumer();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.pro();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                t.consumer();
            }
        }).start();

        try {
            Thread.sleep(3000);
        } catch (Exception e) {
        }

    }
}
