package jvm.test.T11_ProducAndCou;


import java.util.LinkedList;

/**
 * 生产者消费者
 * wait()  notifyAll()
 */
public class T11_ProCou {

    //容器
    LinkedList list = new LinkedList();

    //
    static int MAXSIZE = 10;

    //锁
    static String LOCK = "lock";

    public synchronized void put(Object o) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        //当满了的时候-就等着
        while (list.size() == MAXSIZE) {//用while不用if --if只判断一次-加入条件成立准备操作时，另一个线程来操作---while即使另一线程操作了 还会回去进行判断
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        //没有满就可以生产
        list.add(o);
        // 唤醒消费者--多个消费者
        this.notifyAll();//为什么是notifyAll()   如果是notify()  下次可能只唤醒生产者 如果此时的长度已经到最大值  生产者会卡住 无人唤醒
        //  为什么是notifyAll()  即使生产者卡住，消费者消费完了仍然会唤醒它

    }

    public synchronized Object get() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }
        //当满了的时候-就等着
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (Exception e) {
            }
        }
        //唤醒生产者
        this.notifyAll();

        return list.removeFirst();

    }


    public static void main(String[] args) {
        T11_ProCou t = new T11_ProCou();
        //生产者，当容器满了就等着
        Thread p1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("p1添加");
                    t.put("p1--");
                }
            }
        }, "p1");
        Thread p2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    System.out.println("p2添加");
                    t.put("p2--");
                }
            }
        }, "p2");


        Thread c1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println("c1:" + t.get());
            }
        }, "c1");


        Thread c2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                    System.out.println("c2:" + t.get());

            }
        }, "c2");


        Thread c3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    System.out.println("c3:" + t.get());
                }
            }
        }, "c3");


        //
        p1.start();
        p2.start();
        c1.start();
        c2.start();
        c3.start();


    }


}
