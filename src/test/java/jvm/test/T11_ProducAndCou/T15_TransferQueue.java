package jvm.test.T11_ProducAndCou;

import java.util.concurrent.LinkedTransferQueue;

public class T15_TransferQueue {
    /**
     * 先启动消费者--生产数据时如果有消费者就直接将数据给消费者
     * 如果先启动生产者 但是没有消费者 transfer 就会阻塞
     * add put transfer正常
     */
    static LinkedTransferQueue queue = new LinkedTransferQueue();


    public static void main(String[] args) {
        //new Thread(new Runnable() {
        //    @Override
        //    public void run() {
        //        try {
        //            Object poll = queue.take();
        //            System.out.println(poll);
        //        }catch (Exception e){}
        //    }
        //}).start();

        try {
            queue.transfer("111");
        } catch (Exception e) {
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object poll = queue.take();
                    System.out.println(poll);
                } catch (Exception e) {
                }
            }
        }).start();

    }
}
