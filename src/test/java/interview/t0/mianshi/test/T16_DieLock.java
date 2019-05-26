package interview.t0.mianshi.test;

import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class T16_DieLock {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void die() {
        /**
         * 此处是错误案例
         */


        //jps -l 查进程号
        //jstack 进程号  查异常堆栈信息
        String A = "A";
        String B = "222";

        Thread t1 = new Thread(new MyTask(A, B));
        Thread t2 = new Thread(new MyTask(B, A));

        t1.start();
        t2.start();



    }


}

@Data
class MyTask implements Runnable {

    private String A;
    private String B;

    public MyTask(String A, String B) {
        this.A = A;
        this.B = B;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.A) {
                System.out.println("-----111111");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                synchronized (this.B) {
                    System.out.println("====== 2222222");
                }

            }

        }


    }
}