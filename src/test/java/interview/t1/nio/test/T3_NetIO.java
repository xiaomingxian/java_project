package interview.t1.nio.test;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class T3_NetIO {
    /**
     * BIO
     *
     * @throws Exception
     */
    @Test
    public void client() throws Exception {
        //获取socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9999));
        //获取本地文件通道
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/xxm/develop/workspace/learn/src/test/java/interview/nio/test/T2_Channel.java"), StandardOpenOption.READ);
        //缓存
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //    从文件通道读取，通过socket通道写出
        while (fileChannel.read(buffer) != -1) {
            //    切换为读模式
            buffer.flip();
            socketChannel.write(buffer);
            //清空缓存
            buffer.clear();
        }
        //此处阻塞------传统解决阻塞的方式 shutdownOutput 告诉服务端 输出完了
        //也可以使用非阻塞
        fileChannel.close();
        socketChannel.shutdownOutput();

        //接受服务端响应
        int len = 0;
        while ((len = socketChannel.read(buffer)) != -1) {
            buffer.flip();
            //System.out.println(new String(buffer.array(), 0, len));
            System.out.println(new String(buffer.array(), 0, buffer.limit()));
            buffer.clear();
        }

        //    关闭通道
        socketChannel.close();


    }

    /**
     * 服务端
     * 马士兵写法：客户端联通服务端后[accept] 开启新线程处理数据[避免阻塞]
     *
     * @throws Exception
     */
    @Test
    public void server() throws Exception {
        //服务端socket通道
        ServerSocketChannel open = ServerSocketChannel.open();
        open.bind(new InetSocketAddress(9999));
        FileChannel open1 = FileChannel.open(Paths.get("aaa.java"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //    从socket通道读取，放入文件通道
        SocketChannel accept = open.accept();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (accept.read(buffer) != -1) {
            //切换为读取模式
            buffer.flip();
            open1.write(buffer);
            buffer.clear();
        }
        //响应客户端
        buffer.put("--->服务端数据接受成功".getBytes());
        buffer.flip();
        accept.write(buffer);


        open1.close();
        open.close();
    }
}
