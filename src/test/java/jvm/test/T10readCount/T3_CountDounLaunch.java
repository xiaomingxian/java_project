package jvm.test.T10readCount;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class T3_CountDounLaunch {

    CountDownLatch c = new CountDownLatch(1);//参数为闩数量

    List list = new ArrayList();


    public static void main(String[] args) {
        T3_CountDounLaunch t = new T3_CountDounLaunch();


        //    写线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    t.list.add(i);
                    System.out.println("元素：" + i);
                    if (t.list.size() == 5) {
                        t.c.countDown();
                    }
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
                if(t.list.size()!=5){
                   try {
                       t.c.await();
                   }catch (Exception e){}
                }
                System.out.println("----->数量符合要求");
            }
        }).start();


    }
}
