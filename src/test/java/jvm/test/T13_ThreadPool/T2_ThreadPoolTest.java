package jvm.test.T13_ThreadPool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class T2_ThreadPoolTest {


    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                    }
                    System.out.println(Thread.currentThread().getName());
                }
            });
            System.out.println(service);

            service.shutdown();//等待所有线程执行完毕才会关闭
            System.out.println(service.isTerminated());
            System.out.println(service.isShutdown());

            System.out.println(service);
            try {
                Thread.sleep(30);
            } catch (Exception e) {
            }
            System.out.println(service.isTerminated());
            System.out.println(service.isShutdown());

            System.out.println(service);

            //java.util.concurrent.ThreadPoolExecutor@2ef1e4fa[Running, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
            //false
            //true
            //java.util.concurrent.ThreadPoolExecutor@2ef1e4fa[Shutting down, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
            //false
            //true
            //java.util.concurrent.ThreadPoolExecutor@2ef1e4fa[Shutting down, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 0]
            System.out.println("========================= future");
            FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return 100;
                }
            });
            new Thread(task).start();

            try {
                System.out.println("返回值：" + task.get());

            } catch (Exception e) {
            }
        }

    }
}
