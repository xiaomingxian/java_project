package interview.t1.nio.test;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.channels.AsynchronousChannelGroup.withCachedThreadPool;

public class T7_AIO {

    @Test
    public void server() throws Exception {


        ExecutorService executorService = Executors.newCachedThreadPool();
        AsynchronousChannelGroup threadPool = withCachedThreadPool(executorService, 1);//1表示此处需要一个线程池，可以写多个
        //加入工人线程池
        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(threadPool).bind(new InetSocketAddress(9999));
        //不加入工人线程池
        //AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9999));


        //此操作非阻塞----观察者模式：告诉操作系统，啥时候有客户端连上来就操作这段代码-->CompletionHandler...[这个操作也叫回掉函数]
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            //客户端连接成功--结果交给回掉函数由操作系统去处理
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                serverSocketChannel.accept(null, this);//不写这段代码-下一个客户端连接不上来
                try {
                    System.out.println(client.getRemoteAddress());
                    //   创建缓冲
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //  此处为非阻塞--读取到
                    client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();//可读取
                            //System.out.println(new String(attachment.array(),0,attachment.limit()));
                            System.out.println(new String(attachment.array(), 0, result));
                            client.write(buffer.wrap("---->服务端读取数据成功".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            //客户端连接失败
            public void failed(Throwable exc, Object attachment) {
                System.out.println("------>连接失败");
            }
        });
        while (true) {

        }

    }
}
