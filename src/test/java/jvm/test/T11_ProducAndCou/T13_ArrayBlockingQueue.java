package jvm.test.T11_ProducAndCou;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * 也可以模拟生产者消费者
 * 生产还是用 put  无限阻塞
 */
public class T13_ArrayBlockingQueue {
    static ArrayBlockingQueue queue = new ArrayBlockingQueue(10);


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            queue.add(i);
        }
        //queue.add("--");//java.lang.IllegalStateException: Queue full
        boolean offer = queue.offer("");
        System.out.println("是否添加成功：" + offer);
        try {
            boolean offer1 = queue.offer("", 10, TimeUnit.SECONDS);//延时
            System.out.println("是否添加成功：" + offer1);

        } catch (Exception e) {
        }
    }
}
