package jvm.test.T11_ProducAndCou;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class T14_OtherQueue {
    /**
     *  未验证
     *  取出顺序根据Compareto决定
     */

    static DelayQueue queue = new DelayQueue();

    public static void main(String[] args) {
        queue.put(new MyDely(7));
        queue.put(new MyDely(2));
        queue.put(new MyDely(9));
        queue.put(new MyDely(4));
        queue.put(new MyDely(5));

        for (Object o : queue) {

        try {
            System.out.println(queue.take());

        }catch (Exception e){}
        }


    }

    static class MyDely implements Delayed {
        private long v;

        public MyDely(long v) {
            this.v = System.currentTimeMillis() + v;
        }


        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(v - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.v - o.getDelay(TimeUnit.MILLISECONDS) > 0)
                return 1;
            else if (this.v - o.getDelay(TimeUnit.MILLISECONDS) < 0)
                return -1;
            else
                return 0;

        }

        @Override
        public String toString() {
            return "--->" + v;
        }
    }


}
