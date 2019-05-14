package interview.t1.nio.test;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class T6_Pipe {
    /**
     * 单项通道 数据会被写到sink通道，从source通道读取。
     *
     * @throws Exception
     */

    @Test
    public void pipe() throws Exception {
        Pipe pipe = Pipe.open();
        //写数据
        Pipe.SinkChannel sink = pipe.sink();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("单项管道写数据".getBytes());
        buffer.flip();
        sink.write(buffer);
        buffer.clear();
        //读数据
        Pipe.SourceChannel source = pipe.source();
        int len = source.read(buffer);
        buffer.flip();

        System.out.println("-->读取内容：" + new String(buffer.array(), 0, len));
        //关闭通道
        sink.close();
        source.close();

    }


}
