package jvm.test.T10readCount;

import java.util.ArrayList;
import java.util.List;

public class T1_Volatile {
    /**
     * 思路，使容器变成线程可见
     * 缺点：需要一个线程一直监控，效率低
     */
    volatile List list = new ArrayList();

    public static void main(String[] args) {
        T1_Volatile t = new T1_Volatile();
        //写线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------->写入启动");
                for (int i = 0; i < 10; i++) {
                    t.list.add(i);
                    System.out.println("元素：" + i);
                    // 加入睡眠 防止cpu执行速度过快，看不到读取效果
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();


        //    读线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-------->读取启动");
                while (true) {
                    if (t.list.size() == 5) {
                        System.out.println("-------数量到5");
                        break;
                    }
                }
            }
        }).start();

    }

}
