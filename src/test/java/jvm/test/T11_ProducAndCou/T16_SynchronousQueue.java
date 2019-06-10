package jvm.test.T11_ProducAndCou;

import java.util.concurrent.SynchronousQueue;

public class T16_SynchronousQueue {

    static SynchronousQueue queue = new SynchronousQueue();

    public static void main(String[] args) throws  Exception{
        //先生产也会阻塞
        //queue.put("");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Object take = queue.take();
                    System.out.println(take);
                } catch (Exception e) {
                }
            }
        }).start();

        //queue.add("");//Queue full即使无元素
        try {
            queue.put("---");
            //底层用的也是 transfer
            //public void put(E e) throws InterruptedException {
            //    if (e == null) throw new NullPointerException();
            //    if (transferer.transfer(e, false, 0) == null) {
            //        Thread.interrupted();
            //        throw new InterruptedException();
            //    }
            //}
        } catch (Exception e) {
        }
    }
}
