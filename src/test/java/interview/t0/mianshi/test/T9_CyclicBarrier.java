package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.concurrent.CyclicBarrier;

public class T9_CyclicBarrier {

    /**
     * 集齐7个才能。。。
     */
    @Test
    public void t1() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("------>开始");
        });

        for (int i = 0; i < 7; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("---->" + finalI
                );
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                }
            }).start();
        }


    }

}
