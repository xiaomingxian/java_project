package interview.t1.nio.test;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class T4_NetNio {
    /**
     * 非阻塞式通信
     */


    /**
     * client
     */
    @Test
    public void client() throws Exception {
        //1.创建socket通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9999));


        //   2.将通道切换到非阻塞模式
        socketChannel.configureBlocking(false);

        //        数据传输
        //    3.缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("非阻塞式信息传输".getBytes());
        buffer.flip();

        socketChannel.write(buffer);
        buffer.clear();

        //    4.关闭通道
        socketChannel.close();

    }

    /**
     * server
     */
    @Test
    public void server() throws Exception {
        //1.套接字通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.绑定端口
        serverSocketChannel.bind(new InetSocketAddress(8888));
        //3.非阻塞
        serverSocketChannel.configureBlocking(false);
        //4.选择器
        Selector selector = Selector.open();
        //5.向selector注册channel
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);//监听是通道是否就绪
        //SelectionKey.OP_CONNECT;
        //SelectionKey.OP_READ
        //SelectionKey.OP_WRITE
        //    6.轮训获取选择器上已准备就绪的时间
        while (selector.select() > 0) {
            //selector.select();//马士兵写法--手动轮训？？此操作为阻塞操作
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //7.获取就绪的事件
                SelectionKey next = iterator.next();
                //8.判断什么就绪，此处是准备就绪
                if (next.isAcceptable()) {
                    //接受数据
                    SocketChannel accept = serverSocketChannel.accept();//为此事件建立一个通道
                    accept.configureBlocking(false);//切换阻塞状态
                    //    将通道注册到选择器上
                    accept.register(selector, SelectionKey.OP_READ);
                } else if (next.isReadable()) {
                    //如果是读就绪
                    SocketChannel socketChannel = (SocketChannel) next.channel();
                    socketChannel.configureBlocking(false);
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = socketChannel.read(buffer)) != -1) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }

                }
                //取消选择键
                iterator.remove();//不remove下次轮训过来还会对此时间处理一次
            }

        }


    }

}
