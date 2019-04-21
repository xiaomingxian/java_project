package jvm.test.T12_juc;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 卖票
 */
public class T2_CurrentLinkedQueue {


    static ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

    //ConcurrentLinkedDeque//双向队列
    static {
        for (int i = 0; i < 10000; i++) {
            queue.add("tick" + i);
        }
    }

    public static void main(String[] args) {
        //消费
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!queue.isEmpty()) {//这里的条件如果变判断应该会有问题
                    //如果没值就是null
                    Object poll = queue.poll();//
                    if (null == poll)
                        break;
                    else System.out.println("tick:" + poll);
                }
            }
        }).start();

        //消费
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!queue.isEmpty()) {//这里的条件如果变判断应该会有问题
                    //如果没值就是null
                    Object poll = queue.poll();//
                    if (null == poll) break;//判断后没有继续操作----没有线程安全问题？？？
                    else System.out.println("tick:" + poll);
                }
            }
        }).start();


    }


}
