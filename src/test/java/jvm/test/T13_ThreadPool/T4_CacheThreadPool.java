package jvm.test.T13_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T4_CacheThreadPool {


    public static void main(String[] args) {
        //线程服用：线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
        ExecutorService service = Executors.newCachedThreadPool();

        System.out.println(service);
         for (int i=0;i<10;i++) {
             int finalI = i;
             service.execute(new Runnable() {
                 @Override
                 public void run() {
                     System.out.println("----" + finalI+"  "+Thread.currentThread().getName());
                     try {
                         Thread.sleep(100);
                     }catch (Exception e){}
                 }
             });

         }

        System.out.println(service);
    }
}
