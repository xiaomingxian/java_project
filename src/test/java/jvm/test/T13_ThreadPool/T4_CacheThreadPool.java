package jvm.test.T13_ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T4_CacheThreadPool {


    public static void main(String[] args) {
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
