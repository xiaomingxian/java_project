package interview.t1.nio.test;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import org.junit.Test;

public class T8_Netty {

    @Test
    public void client() {
        NioEventLoopGroup worksGroup = new NioEventLoopGroup(10);

        Bootstrap client = new Bootstrap();

        client.group(worksGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {//初始化连接
                        socketChannel.pipeline().addLast(new ClinetHandler());
                    }
                });
        try {
            ChannelFuture channelFuture = client.connect("localhost", 9999).sync();//异步连接
            channelFuture.channel().close().sync();//异步关闭

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            worksGroup.shutdownGracefully();
        }

    }


    @Test
    public void server() {
        //相当于nio模型中的selector
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        //工作线程池
        NioEventLoopGroup worksGroup = new NioEventLoopGroup();
        //通过它对server 启动之前的一些配置
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(boosGroup, worksGroup)//指定线程池类型--第一个负责连接，第二个负责连接之后的IO处理
                .channel(NioServerSocketChannel.class)//通道类型
                .childHandler(new ChannelInitializer<SocketChannel>() {//当每一个客户端连接上来之后给它一个监听器进行处理
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline().addLast(new Hander());//这个通道一旦初始化就添加此通道的处理器--对数据做出炉
                    }
                });
        try {

            ChannelFuture sync = serverBootstrap.bind(9999).sync();//异步绑定
            Thread.sleep(100000);
            sync.channel().close().sync();//异步关闭
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boosGroup.shutdownGracefully();
            worksGroup.shutdownGracefully();
        }
    }

}

/**
 * 服务端通道处理器
 */
class Hander extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("----------->读取数据成功");
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(msg);//写回客户端
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("---------> 异常处理");
        cause.printStackTrace();
        ctx.close();

    }
}

/**
 * 客户端通道处理器
 */
class ClinetHandler extends ChannelInboundHandlerAdapter {

    @Override//通道建立
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("-------->客户端与服务端通道建立");
        ChannelFuture channelFuture = ctx.writeAndFlush(Unpooled.copiedBuffer("--->客户端发送信息".getBytes()));
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                System.out.println("---> 客户端消息发送成功");
            }
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        System.out.println("---->读取服务端数据");
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString(CharsetUtil.UTF_8));

        ctx.close();
    }


}