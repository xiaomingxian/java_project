package interview.t1.nio.test;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class T2_Channel {
    /**
     * 用传统的输入流输出流获取通道
     * jvm内存demo,也可以直接内存
     */
    @Test
    public void t1_copy() {
        try {
            FileInputStream in = new FileInputStream("/Users/xxm/Desktop/3.直接缓冲区与非直接缓冲区.jpg");
            FileOutputStream out = new FileOutputStream("/Users/xxm/develop/workspace/learn/src/test/java/interview/nio/pic/3.直接缓冲区与非直接缓冲区.jpg");

            //1.获取通道
            FileChannel inChannel = in.getChannel();
            FileChannel outChannel = out.getChannel();

            //2. 指定缓冲区大小
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (inChannel.read(buffer) != -1) {
                //切换为读模式
                buffer.flip();
                //写数据
                outChannel.write(buffer);
                //    清空缓冲区
                buffer.clear();
            }

            //    关闭通道，流--正确写法写在finally 并做判断
            outChannel.close();
            inChannel.close();
            in.close();
            out.close();
            System.out.println("--->复制成功");
        } catch (Exception e) {
        }
    }

    /**
     * File工具获取通道
     * 直接缓冲区demo
     */
    @Test
    public void fileUtils() {

        try {
            //读取通道------------------可变参，多个拼接------------- 枚举类[读还是写]
            FileChannel readChannel = FileChannel.open(Paths.get("/Users/xxm/Desktop/通道.jpg"), StandardOpenOption.READ);
            FileChannel writeChannel = FileChannel.open(Paths.get("/Users/xxm/develop/workspace/learn/src/test/java/interview/nio/pic/4.通道.jpg")
                    , StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);//不存在就创建，存在就报错---可变参[多种模式]
            //内存映射文件---直接内存
            MappedByteBuffer in = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());
            //    写
            MappedByteBuffer out = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, readChannel.size());

            //    从通道获取数据--再通过输出通道将数据放入直接内存--通过Channel直接读入磁盘
            byte[] size = new byte[in.limit()];
            in.get(size);
            out.put(size);
            //
            readChannel.close();
            writeChannel.close();
            System.out.println("复制完毕");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通道间的 from to
     */
    @Test
    public void channelTrsform() {
        try {

            FileChannel readChannel = FileChannel.open(Paths.get("/Users/xxm/Desktop/获取通道的几种方式.jpg"), StandardOpenOption.READ);
            FileChannel writeChannel = FileChannel.open(Paths.get("/Users/xxm/develop/workspace/learn/src/test/java/interview/nio/pic/5.获取通道的几种方式.jpg")
                    , StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);//不存在就创建，存在就报错---可变参[多种模式]

            //readChannel.transferTo(0, readChannel.size(), writeChannel);
            //或者
            writeChannel.transferFrom(readChannel, 0, readChannel.size());

            readChannel.close();
            writeChannel.close();
            System.out.println("---ok---");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 分散，聚集
     */
    @Test
    public void fJ() {
        try {
            //分散读取
            RandomAccessFile rw = new RandomAccessFile("/Users/xxm/develop/workspace/learn/src/test/java/interview/nio/test/T1_Base.java", "rw");
            FileChannel channel = rw.getChannel();
            //定义多个缓冲区
            ByteBuffer b1 = ByteBuffer.allocate(100);
            ByteBuffer b2 = ByteBuffer.allocate(1024);
            ByteBuffer[] bs = {b1, b2};
            channel.read(bs);
            //    读取每一个缓冲区
            for (ByteBuffer b : bs) {
                b.flip();
            }
            System.out.println(new String(bs[0].array(), 0, bs[0].limit()));
            System.out.println("----------------");
            System.out.println(new String(bs[1].array(), 0, bs[1].limit()));

            //     聚集
            RandomAccessFile write = new RandomAccessFile("/Users/xxm/Desktop/1.java", "rw");
            FileChannel channel1 = write.getChannel();
            channel1.write(bs);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Charset编码解码
     */
    @Test
    public void bianmajiema() throws Exception {
        SortedMap<String, Charset> cs = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> entries = cs.entrySet();
        for (Map.Entry<String, Charset> m : entries) {
            //System.out.println(m.getKey()+"---"+m.getValue());
        }

        String s = "阿电磁辐射的城市";
        Charset gbk = Charset.forName("GBK");
        //    获取编码器
        CharsetEncoder en = gbk.newEncoder();
        //    获取解码器
        CharsetDecoder de = gbk.newDecoder();


        //
        CharBuffer charBuffer = CharBuffer.allocate(1024);
        charBuffer.put(s);
        // 得读取后进行编码
        charBuffer.flip();
        ByteBuffer encode = en.encode(charBuffer);
        System.out.println(encode);
        //    解码
        charBuffer.flip();
        CharBuffer decode = de.decode(encode);
        System.out.println(decode.toString());


    }

}
