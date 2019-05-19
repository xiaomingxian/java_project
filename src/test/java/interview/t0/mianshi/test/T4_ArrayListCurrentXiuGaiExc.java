package interview.t0.mianshi.test;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.collect.CopyOnWriteHashSet;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
public class T4_ArrayListCurrentXiuGaiExc {

/**
 * 装逼套路
 *
 * 1 故障现象
 *          java.util.ConcurrentModificationException
 *
 * 2 导致原因
 *      并发争抢修改[花名册签名] 导致数据并发修改异常
 *
 * 3 解决方案
 *          new Vector<Object>();
 *          Collections.synchronizedList(new ArrayList<>());
 *          juc 下  CopyOnWriteArrayList   add 方法加了手动锁
 *
 * 4 优化建议[同样的错误不犯两次]
 *
 *
 */

    /**
     * CopyOnWriteArrayList add源码
     *   public boolean add(E e) {
     *         final ReentrantLock lock = this.lock;
     *         lock.lock();
     *         try {
     *             Object[] elements = getArray();
     *             int len = elements.length;
     *             Object[] newElements = Arrays.copyOf(elements, len + 1);//写时复制的体现
     *             newElements[len] = e;
     *             setArray(newElements);
     *             return true;//写完通知可以读  即引用指向改变
     *         } finally {
     *             lock.unlock();
     *         }
     *     }
     */

    /**
     * 写时复制  写的时候复制一份 写完再将引用指向复制后的地址
     * 读不涉及安全问题
     */


    /**
     * arrayList并发修改异常
     * Exception in thread "Thread-10" java.util.ConcurrentModificationException
     */
    @Test
    public void t1() {
        //一致性问题
        //ArrayList<Object> list = new ArrayList<>();
        //效率低
        //List<Object> list = new Vector<Object>();
        //List<Object> list = Collections.synchronizedList(new ArrayList<>());
        //juc
        List<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {

            new Thread(() -> {
                list.add(UUID.randomUUID().toString());
                System.out.println(list);
            }).start();

        }


        try {
            Thread.sleep(100);
        } catch (Exception e) {
        }

    }

    /**
     * 与ArrayList的解决方法类似
     * HashSet
     * <p>
     * public HashSet() {
     * map = new HashMap<>();
     * }
     * <p>
     * ////add方法 key是传入的值 value 是一个Object常量
     * public boolean add(E e) {
     * return map.put(e, PRESENT)==null;
     * }
     */
    @Test
    public void t2() {
        HashSet<Object> objects = new HashSet<>();

        CopyOnWriteHashSet<Object> objects1 = new CopyOnWriteHashSet<>();
        /**
         *   public CopyOnWriteHashSet() {
         *         this(new CopyOnWriteHashMap());
         *     }
         */
    }

    /**
     * 类似
     * HashMap
     */
    @Test
    public void t3() {
        new ConcurrentHashMap<>();
    }
}
