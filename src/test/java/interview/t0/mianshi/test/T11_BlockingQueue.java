package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

public class T11_BlockingQueue {
/**
 * api
 */


    /**
     * 抛出异常型
     * add()
     * remove()
     */
    @Test
    public void tEx() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        queue.add("");
        queue.add("");
        queue.add("");
        //queue.add("");//java.lang.IllegalStateException: Queue full
        queue.remove();
        queue.remove();
        queue.remove();
        //queue.remove();//java.util.NoSuchElementException

    }
}
