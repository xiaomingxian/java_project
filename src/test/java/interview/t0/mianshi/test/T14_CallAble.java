package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class T14_CallAble {
    @Test
    public void t1() throws Exception{
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {

                try{Thread.sleep(1000);}catch (Exception e){}
                System.out.println("----执行体，验证只执行一次");

                return 100;
            }
        });
        new Thread(task).start();
        new Thread(task).start();

        //2 是否完毕--没有完毕就等着
        while (!task.isDone()){
            //System.out.println("没有完毕-在等待");
        }


        //1 阻塞等待
        System.out.println(task.get());

        //同一个task会复用-只执行一次

    }


}
