package com.asyf.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        int port = Integer.parseInt("8088"); //1设置端口值
        new EchoServer(port).start(); //2呼叫服务器的 start() 方法
    }

    public void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //3创建 EventLoopGroup
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup) //4创建 ServerBootstrap
                    .channel(NioServerSocketChannel.class) //5指定使用 NIO 的传输 Channel
                    .localAddress(new InetSocketAddress(port)) //6设置 socket 地址使用所选的端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //7添加 EchoServerHandler 到 Channel 的 ChannelPipeline
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS))
                                    .addLast(new EchoServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind().sync(); //8绑定的服务器;sync 等待服务器关闭
            System.out.println(EchoServer.class.getName() + " started and listen on "
                    + f.channel().localAddress());
            ChannelFuture sync = f.channel().closeFuture().sync();//9关闭 channel 和 块，直到它被关闭
            System.out.println("关闭");
        } finally {
            System.out.println("finally执行");
            bossGroup.shutdownGracefully().sync(); //10关闭 EventLoopGroup，释放所有资源
            workerGroup.shutdownGracefully().sync();
        }
    }

}
