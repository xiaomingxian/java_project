package jvm.test.T13_ThreadPool;

import javafx.concurrent.Task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T3_FixedThreadPool {

    public static void main(String[] args) {
        //固定线程池
        ExecutorService service = Executors.newFixedThreadPool(5);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("1111111");
            }
        };
        Future<?> submit = service.submit(runnable);
        Future<Object> submit1 = service.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("22222");
                return "1";
            }
        });

        //service.execute(new Task<Object>() {
        //    @Override
        //    protected Object call() throws Exception {
        //        System.out.println("33333");
        //        return "22";
        //    }
        //});
        try {
            System.out.println(submit.get());
            System.out.println(submit1.get());
        } catch (Exception e) {
        }

    }
}
