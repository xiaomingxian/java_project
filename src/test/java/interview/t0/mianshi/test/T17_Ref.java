package interview.t0.mianshi.test;

import org.junit.Test;
import pojo.shiro.Permission;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class T17_Ref {


    /**
     * 软引用
     * 没有演示出来
     * -XX:+PrintGCDetails -Xms1m -Xms1m
     */
    @Test
    public void soft() {
        Object a = new Permission();
        SoftReference<Object> objectSoftReference = new SoftReference<>(a);
        a = null;//取消强引用
        byte[] b = new byte[500 * 1024 * 1024];

        System.out.println(a);
        System.out.println(objectSoftReference.get());


    }

    /**
     * 弱引用
     */
    @Test
    public void week() {
        Object a = new Permission();
        WeakReference weakReference = new WeakReference(a);
        a = null;//使强引用被失去引用
        System.gc();
        System.out.println(a);
        System.out.println(weakReference.get());
    }

    /**
     * 虚引用--结合引用队列使用
     */
    @Test
    public void xu(){

        Object o = new Object();


        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        PhantomReference<Object> objectPhantomReference = new PhantomReference(o,queue);


        System.out.println("虚引用"+objectPhantomReference.get());
        System.out.println("引用队列"+queue.poll());

        o=null;
        System.gc();
        System.out.println("--------------gc--------------");
        System.out.println("虚引用"+objectPhantomReference.get());
        //回收的时候会被放到引用队列中
        System.out.println("引用队列"+queue.poll());





    }



}
