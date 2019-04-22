package jvm.test.T13_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T6_WorkStealing {


    public static void main(String[] args) {
        System.out.println("cpu核数：" + Runtime.getRuntime().availableProcessors());

        /**
         * newWorkStealingPool里面的线程是精灵线程 daemon  主线程结束了它可能还在后台运行
         *  System.in.read();  得阻塞一下，不然看不到输出
         *
         * newWorkStealingPool 底层是 ForkJoinPool
         * 是对 ForkJoinPool 做了一层封装，封装了一层好用的api
         */
        ExecutorService service = Executors.newWorkStealingPool(4);
        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        try {
            System.in.read();
        } catch (Exception e) {
        }

    }

    static class R implements Runnable {
        int i;

        public R(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(i);
                System.out.println("---->当前执行线程：" + Thread.currentThread().getName());
            } catch (Exception e) {

            }

        }
    }
}
