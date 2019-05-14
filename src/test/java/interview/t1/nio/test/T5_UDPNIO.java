package interview.t1.nio.test;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class T5_UDPNIO {
    public static void main(String[] args) {
        T5_UDPNIO t5_udpnio = new T5_UDPNIO();

        try {
            t5_udpnio.t1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * udp
     */
    @Test
    public void t0() throws Exception {
        DatagramChannel dc = DatagramChannel.open();

        dc.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(1024);

        Scanner scan = new Scanner(System.in);

        while (scan.hasNext()) {
            String str = scan.next();
            buf.put((new Date().toString() + ":\n" + str).getBytes());
            buf.flip();
            dc.send(buf, new InetSocketAddress("127.0.0.1", 9999));
            buf.clear();
        }

        dc.close();
    }

    @Test
    public void t1() throws Exception {


        DatagramChannel channel = DatagramChannel.open();
        //    设置非阻塞
        channel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.hasNext());
        while (scanner.hasNext()) {
            String next = scanner.next();
            buffer.put((LocalDateTime.now() + "\n" + next).getBytes());
            buffer.flip();
            channel.send(buffer, new InetSocketAddress("localhost", 9999));
            buffer.clear();
        }
        channel.close();


    }

    @Test
    public void t2() throws Exception {

        DatagramChannel channel = DatagramChannel.open();
        channel.bind(new InetSocketAddress(9999));
        //    设置非阻塞
        channel.configureBlocking(false);

        //    选择器
        Selector selector = Selector.open();
        //    注册选择器
        channel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            if (iterator.hasNext()) {
                //    判断是否读取就绪
                SelectionKey next = iterator.next();
                if (next.isReadable()) {
                    //    读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.receive(buffer);
                    buffer.flip();
                    System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    buffer.clear();
                }
                iterator.remove();

            }

        }


    }

}
