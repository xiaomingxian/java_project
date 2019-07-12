package jvm.book_test;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class T1OOMTest {

    /**
     * *********************************************  堆溢出  *********************************************
     * 最小堆内存  最大堆内存   转储内存堆快照
     * -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
     * 结果：
     * java.lang.OutOfMemoryError: GC overhead limit exceeded
     * 以上是对象回收效率低下导致的异常
     * GC overhead limit exceeded  超过98%的时间来做gc,回收效率不到2%
     */
    @Test
    public void t1() {
        ArrayList<T1OOMTest> list = new ArrayList<>();
        while (true) list.add(new T1OOMTest());
    }

    /**
     * ********************************************* 栈溢出 *********************************************
     * StackOverflowError,OutOfMemoryError
     * 线程请求深度大于栈的深度，扩展栈时无法申请到足够的内存空间
     */
    /**
     * 单线程下都是：
     * StackOverflowError
     * 栈大小
     * -Xss180k
     */
    @Test
    public void stackOverflow() {
        T1OOMTest t1OOMTest = new T1OOMTest();
        try {
            t1OOMTest.digui();

        } catch (Throwable e) {
            System.out.println("------>栈深度：：" + t1OOMTest.stackLength);
        }
    }

    private int stackLength = 0;

    private void digui() {
        stackLength++;
        digui();
    }

    /**
     * 多线程模拟
     * 操作系统分配给每个进程的内存是有限制的 比如32位windows限制位2GB
     * jvm进程：2G=Xmx+MaxPermSize+栈[java,native，线程]+程序计数器[忽略]+线程
     * 单线程只有一个栈，多线程则为每个线程都分配一个栈，并且这些栈的地址不同
     * 栈 oom
     * <p>
     * 模拟：将栈的大小设置大些--线程被分配的空间就表小
     * windows虚拟机上 java线程是映射到操作系统的内核上的，以上操作可能会造成操作系统假死
     * -Xss10m
     * 结果：
     * java.lang.OutOfMemoryError: unable to create new native thread
     * <p>
     * 调优：缩小堆与栈的容量
     */
    @Test
    public void stackOOM() {
        while (true) {
            new Thread(() -> {
                doSomThing();
            }).start();
        }

    }

    private void doSomThing() {
    }

    /**
     * *********************************************  方法区和常量池溢出  *********************************************
     * jdk1.6及之前的版本，常量池在永久代中 可用参数：-XX:PermSize=10m -XX:MaxPermSize=10m
     * jdk1.8常量在堆中[永久代参数不可用][此处用堆参数测试] 此处结果与堆溢出效果一致
     */
    @Test
    public void methodAndConstOOM() {

        //使用list保持常量引用，避免full gc 回收
        ArrayList<String> list = new ArrayList<>();

        int i = 1;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }

    }

    /**
     * 直接内存溢出[未模拟出来]
     * -XX:MaxDirectMemorySize=1m
     * Unself类模拟[使用此类去申请内存]
     */

    public static void main(String[] args) throws Exception {
        //第一个成员变量  private static final Unsafe theUnsafe;
        Field unsafeFeild = Unsafe.class.getDeclaredFields()[0];
        //暴力访问私有成员变量
        unsafeFeild.setAccessible(true);
        //unsafe类限定 getUnsafe()只有引导类加载起才会访问实例
        Unsafe unsafe = (Unsafe) unsafeFeild.get(null);
        //1m
        while (true) unsafe.allocateMemory(1024 * 1024);
    }

    /**
     * nio模拟
     * -XX:MaxDirectMemorySize=1m
     * java.lang.OutOfMemoryError: Direct buffer memory
     */
    @Test
    public  void  nio(){
        ByteBuffer.allocateDirect(10*1024*1024);
    }




}
