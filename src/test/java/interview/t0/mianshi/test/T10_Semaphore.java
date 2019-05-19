package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class T10_Semaphore {


    /**
     * 多对多 多个线程抢多个资源
     * 资源可复用
     * eg:抢车位
     */
    @Test
    public void t1() {
        //三个车位
        Semaphore semaphore = new Semaphore(3);
        //6个车
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();//抢到车位
                    System.out.println(Thread.currentThread().getName() + " 抢到车位");
                    Thread.sleep(400);
                    System.out.println(Thread.currentThread().getName() + " 离开车位");
                } catch (Exception e) {
                } finally {
                    //释放资源---空出停车位
                    semaphore.release();
                }

            }, "线程-" + i).start();

            try {
                Thread.sleep(500);

            } catch (Exception e) {
            }
        }

    }
}
