package com.asyf.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        final String host = "127.0.0.1";
        final int port = Integer.parseInt("8088");
        new EchoClient(host, port).start();
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap(); //1创建 Bootstrap
            b.group(group) //2
                    .channel(NioSocketChannel.class) //3
                    .option(ChannelOption.TCP_NODELAY, true)
                    .remoteAddress(new InetSocketAddress(host, port)) //4
                    .handler(new ChannelInitializer<SocketChannel>() { //5
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast("ping", new IdleStateHandler(0, 4, 0, TimeUnit.SECONDS))
                                    .addLast(new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync(); //6
            f.channel().closeFuture().sync(); //7
        } finally {
            group.shutdownGracefully().sync(); //8
        }
    }

}
