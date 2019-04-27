package java8.test;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

public class T6_ForkJoin {

    /**
     * 计算的临界值要取值合适
     * forkJoin 与 普通for循环
     *
     * @param args
     */
    public static void main(String[] args) {
        Instant start = Instant.now();
        //forkJoin的线程池的创建与其他线程池相比-比较特殊
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> submit = pool.submit(new ForkJ(1, 1000000000L));
        try {
            System.out.println(submit.get());
            Instant end = Instant.now();
            System.out.println("计算时间：" + Duration.between(start, end).toMillis());
        } catch (Exception e) {
        }

        System.out.println("------------- 常规计算  -----------------");
        Instant s = Instant.now();
        long sum = 0;
        for (long i = 1; i <= 1000000000L; i++) {
            sum += i;
        }
        Instant e = Instant.now();
        System.out.println(sum);
        System.out.println("计算时间：" + Duration.between(s, e).toMillis());


    }

    @Test
    public void streamParale() {
        Instant start = Instant.now();
        long reduce = LongStream.rangeClosed(1, 1000000000L)
                .parallel()//并行执行--底层是forkJoin
                .reduce(0, Long::sum);
        Instant end = Instant.now();

        System.out.println(reduce);
        System.out.println("执行时间：" + Duration.between(start, end).toMillis());

    }

    /**
     * 并行计算
     */

    static class ForkJ extends RecursiveTask<Long> {


        long start;
        long end;

        long STOP = 1000000;

        public ForkJ(long start, long end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected Long compute() {

            if (end - start <= STOP) {
                //计算
                long sum = 0;
                for (long i = start; i <= end; i++) {

                    sum += i;
                }
                return sum;
            } else {
                //拆分
                long mid = (start + end) / 2;
                ForkJ left = new ForkJ(start, mid);
                left.fork();

                ForkJ right = new ForkJ(mid + 1, end);
                right.fork();

                return left.join() + right.join();

            }
        }
    }

}
