package jvm.test.T10readCount;

import java.util.ArrayList;
import java.util.List;

public class T2_WatiNotify {
    /**
     * 思路：当数量不满足要求时 等着
     * 当满足要求时      进行提示
     */

    List list = new ArrayList();

    private static String LOCK = "lock";

    public static void main(String[] args) {
        T2_WatiNotify t = new T2_WatiNotify();

        //     写线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------->写线程启动");
                synchronized (LOCK) {
                    for (int i = 0; i < 10; i++) {
                        t.list.add(i);
                        System.out.println("添加元素：" + i);
                        try {
                            if (t.list.size() == 5) {
                                //唤醒读线程
                                LOCK.notify();
                                //    释放锁
                                LOCK.wait();
                            }
                            Thread.sleep(100);
                        } catch (Exception e) {
                        }
                    }
                    //唤醒读线程
                    LOCK.notify();
                }
            }
        }).start();
        //    读线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------->读线程启动");
                synchronized (LOCK) {
                    try {
                        if (t.list.size() != 5) {
                            LOCK.wait();
                        } else {
                            System.out.println("----->数量到达5");
                            //唤醒添加线程，释放锁
                            LOCK.notify();
                            LOCK.wait();
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }).start();


    }

}
