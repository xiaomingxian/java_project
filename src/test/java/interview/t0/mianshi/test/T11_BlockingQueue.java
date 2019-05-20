package interview.t0.mianshi.test;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class T11_BlockingQueue {
/**
 * api
 */


    /**
     * 抛出异常型
     * add()
     * remove()
     * element()  检查 拿到第一个元素
     * -----------------------------
     * 返回boolean
     * offer("xxx")添加失败返回false
     * pool()移除失败返回null
     * peek() 检查获取第一个元素
     * ----------------------------
     * 阻塞
     * put(e)//超出大小-阻塞
     * take();//没有元素阻塞
     * 没有检查方法
     * ----------------------------
     * 超时--过时不候(存取)
     */
    @Test
    public void tEx() {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
        //Ex(queue);//异常组
        //boo(queue);//boolean组
        //zuSe(queue);//阻塞
        chaShi(queue);//超时
    }

    @Test
    public void sych() {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        new Thread(() -> {
            try {

                System.out.println("--->存入1");
                queue.put("1");
                System.out.println("--->存入2");
                queue.put("2");
                System.out.println("--->存入3");
                queue.put("3");

            } catch (Exception e) {
            }
        }).start();
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("==>取出："+queue.take());
                Thread.sleep(1000);
                System.out.println("==>取出："+queue.take());
                Thread.sleep(1000);
                System.out.println("==>取出："+queue.take());
            } catch (Exception e) {
            }
        }).start();

        try {
            Thread.sleep(4000);

        } catch (Exception e) {
        }

    }

    private void chaShi(ArrayBlockingQueue<String> queue) {
        try {
            queue.offer("", 1, TimeUnit.MILLISECONDS);
            queue.offer("", 1, TimeUnit.MILLISECONDS);
            queue.offer("", 1, TimeUnit.MILLISECONDS);
            System.out.println(queue.offer("", 1, TimeUnit.SECONDS));//只阻塞1秒

            System.out.println("==========");
            queue.poll(1, TimeUnit.MILLISECONDS);
            queue.poll(1, TimeUnit.MILLISECONDS);
            queue.poll(1, TimeUnit.MILLISECONDS);
            queue.poll(1, TimeUnit.SECONDS);

        } catch (Exception e) {
        }


    }

    private void zuSe(ArrayBlockingQueue<String> queue) {
        try {
            queue.put("a");
            queue.put("b");
            queue.put("c");
            //queue.put("d");

            queue.take();
            queue.take();
            queue.take();
            //queue.take();


        } catch (Exception e) {
        }


    }

    private void boo(ArrayBlockingQueue<String> queue) {

        queue.offer("a");
        queue.offer("b");
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("d"));
        //检查
        System.out.println(queue.peek());
        //移除
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("--检查" + queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }

    public void Ex(Queue queue) {
        queue.add("a");
        queue.add("b");
        queue.add("c");

        System.out.println(queue.element());
        //queue.add("");//java.lang.IllegalStateException: Queue full
        queue.remove();
        queue.remove();
        queue.remove();
        //queue.remove();//java.util.NoSuchElementException

    }
}
