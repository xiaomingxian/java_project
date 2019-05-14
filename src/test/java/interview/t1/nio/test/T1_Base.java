package interview.t1.nio.test;

import org.junit.Test;

import java.nio.ByteBuffer;

public class T1_Base {
    /**
     * 1.缓冲区[Buffer]: 在NIO中负责存储数据，缓冲区是数组，用于存储不同类型的数据
     * 根据类型不同提供了相应的缓冲区[boolean除外]
     * ByteBuffer
     * CharBuffer
     * ShortBuffer
     * IntBuffer
     * LongBuffer
     * FloatBuffer
     * DoubleBuffer
     *
     *
     * 上述几乎都是通过allocate()获取缓冲区
     *
     * 2.put()  存
     *  get()    取
     *
     *  3.0<=mark<=postion<=limit<=capcity
     *
     *  4.直接缓冲区[物理映射文件]，非直接缓冲区[jvm中]
     */

    /**
     * 核心属性
     * capacity 容量-一旦声明不可改变
     * limit    表示缓冲区中可以操作的数据的大小，limit后不可读写
     * position 当前位置
     */
    @Test
    public void t1() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println("-----------存放数据-----------");
        byteBuffer.put(new byte[5]);
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println("-----------读取数据-----------");
        //先转换为读
        byteBuffer.flip();
        //读取三次
        //byteBuffer.get();
        //byteBuffer.get();
        //byteBuffer.get();
        //读取指定长度
        byte[] size = new byte[byteBuffer.limit()];
        byteBuffer.get(size, 0, 3);
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println("----------重复读数据 rewind --------");
        byteBuffer.rewind();
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println("----------清空缓冲区 clear  ---------");
        //但缓冲区的数据还存在，处于被遗忘状态----只是核心属性的指针回到了原始状态
        byteBuffer.clear();
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());

        System.out.println("数据没有被清除还可以被读取到：" + byteBuffer.get());

    }

    @Test
    public void mark() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("abcde".getBytes());
        //切换到读状态
        byteBuffer.flip();
        System.out.println("-------------- mark reset后恢复到mark位置 ---------------");
        System.out.println(byteBuffer.position());
        //读取几个-再记录位置
        byte[] size = new byte[byteBuffer.limit()];
        byteBuffer.get(size, 0, 2);
        System.out.println(byteBuffer.position());
        byteBuffer.mark();
        byteBuffer.get(size, 2, 3);
        byteBuffer.reset();

        System.out.println(byteBuffer.position());


    }

    @Test
    public void direct(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        boolean direct = byteBuffer.isDirect();
        System.out.println(direct);
    }
}
