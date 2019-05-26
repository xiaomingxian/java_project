package interview.t0.mianshi.test;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class T19_Error {


    /**
     * 大部分时间都用来做gc而回收效率过低
     * Exception in thread "main" java.lang.OutOfMemoryError: GC overhead limit exceeded
     * 现象：不断的fullGc
     */
    @Test
    public void gcOverHeadLimit() {
        int i = 0;
        ArrayList list = new ArrayList();

        while (true) {

            try {
                list.add(String.valueOf(++i).intern());
            } catch (Throwable t) {
                System.out.println("----" + list.size());
                t.printStackTrace();
            }


        }


    }

    /**
     * -XX:MaxDirectMemorySize=5m
     * java.lang.OutOfMemoryError: Direct buffer memory
     */
    @Test
    public void directMemmory() {
        //物理内存的 1/4
        System.out.println("最大堆外内存大小：" + sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024 / 1024 + " GB");


        ByteBuffer.allocateDirect(10 * 1024 * 1024);


    }


    /**
     * 同一个进程中 的 线程上限
     * java.lang.OutOfMemoryError: unable to create new native thread
     * mac:线程上限测试结果：~~~~~~~~:4075
     * linux:理论：1024
     */
    @Test
    public void xiancheng() {

        for (int i = 0; ; i++) {

            new Thread(() -> {
                try {
                    //目的--保证线程不推出
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (Exception e) {
                }
            }).start();

            System.out.println("~~~~~~~~:" + i);

        }

    }


    //静态内部类
    static class Meta {
    }

    /**
     * 元空间oom
     * 利用cglib产生出 静态内部类的代理类  类信息放在元空间
     * -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
     * <p>
     * <p>
     * ----类信息个数：465
     * java.lang.OutOfMemoryError: Metaspace
     */
    @Test
    public void metaspace() {
        for (int i = 0; ; i++) {
            try {
                Enhancer enhancer = new Enhancer();
                //父类
                enhancer.setSuperclass(Meta.class);
                //是否缓存
                enhancer.setUseCache(false);
                //回掉函数
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return method.invoke(o, objects);
                    }
                });

                enhancer.create();
            } catch (Throwable t) {
                System.out.println("----类信息个数：" + i);

                t.printStackTrace();
                break;
            }


        }


    }
}
