package jvm.test.T13_ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T5_SchedulePool {
    /**
     * ScheduledExecutorService
     * 线程池中的线程可复用
     * @param args
     */
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(3);
        //固定频率执行
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("1111");
            }
        }, 1, 3, TimeUnit.SECONDS);


    }
}
