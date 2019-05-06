package design_parrent.test;

import design_parrent.pojo.p1_single.Single1;
import design_parrent.pojo.p1_single.Single2;
import design_parrent.pojo.p1_single.Single4;
import design_parrent.pojo.p1_single.Single5;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.util.concurrent.CountDownLatch;

public class T1_Singleton {
    /**
     * 饿汉
     */
    @Test
    public void ehan() {
        Single1 instance = Single1.getInstance();
        Single1 instance2 = Single1.getInstance();

        System.out.println(instance == instance2);
    }

    /**
     * 同步 懒汉
     */
    @Test
    public void lanh() {
        Single2 instance = Single2.getInstance();
        Single2 instance2 = Single2.getInstance();
        System.out.println(instance == instance2);
    }
    /**
     * 双重检测--存在问题
     */


    /**
     * 静态内部类
     */
    @Test
    public void staticNeiBUClass(){

        Single4 instance = Single4.getInstance();
        Single4 instance2 = Single4.getInstance();

        System.out.println(instance==instance2);

    }

    /**
     * 枚举 基于JVM底层实现，是个天然的单例
     */
    @Test
    public void meiju() {
        System.out.println(Single5.VALUE == Single5.VALUE);
    }

    /**
     * 反射破解
     */
    @Test
    public void fanshe() throws Exception {
        Class<Single1> single1Class = (Class<Single1>) Class.forName("design_parrent.pojo.p1_single.Single1");

        Constructor<Single1> declaredConstructor = single1Class.getDeclaredConstructor(null);//获取无餐构造

        declaredConstructor.setAccessible(true);//跳过私有

        Single1 single1 = declaredConstructor.newInstance();
        Single1 single1_2 = declaredConstructor.newInstance();

        System.out.println(single1 == single1_2);
    }

    /**
     * 私有构造加上校验防止反射创建
     */
    @Test
    public void fangfanshe() throws Exception {
        Single2 instance = Single2.getInstance();


        Class<Single2> single1Class = (Class<Single2>) Class.forName("design_parrent.pojo.p1_single.Single2");
        Constructor<Single2> declaredConstructor = single1Class.getDeclaredConstructor(null);

        declaredConstructor.setAccessible(true);

        Single2 single2_1 = declaredConstructor.newInstance();
        Single2 single2_2 = declaredConstructor.newInstance();
        Single2 single2_3 = declaredConstructor.newInstance();


    }

    /**
     * 反序列化测试
     */
    @Test
    public void fanxulie() throws Exception {

        Single1 instance = Single1.getInstance();

        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("objectSer.txt"));
        os.writeObject(instance);
        os.close();
        //    读取序列化内容

        ObjectInputStream oi = new ObjectInputStream(new FileInputStream("objectSer.txt"));
        Single1 o = (Single1) oi.readObject();
        oi.close();

        System.out.println(instance == o);

    }


    /**
     * 性能测试
     * 懒汉性能最高，比其他高1-2个数量级
     */
    @Test
    public void xingneng() throws Exception {
        long start = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(10);
        int threadNum = 10;//线程数量

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    Single1.getInstance();//  饿汉
                    //Single2.getInstance();//  懒汉
                    countDownLatch.countDown();
                }
            }).start();

        }


        countDownLatch.await();// 直到计数器为0
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));

    }

}
