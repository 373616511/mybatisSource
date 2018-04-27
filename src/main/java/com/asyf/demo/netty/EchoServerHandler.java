package com.asyf.demo.netty;

import com.asyf.demo.mongodb.MongoDBUtil;
import com.asyf.util.SerializeUtil;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.bson.Document;
import org.bson.types.Binary;

import java.util.HashMap;
import java.util.Map;

@ChannelHandler.Sharable //1@Sharable  标识这类的实例之间可以在 channel 里面共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    // private static final Map<String, Channel> map = new HashMap<>();
    private int loss_connect_time = 0;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("通道激活，channel=" + ctx.channel().id());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server channelRead.." + ctx.name());
        System.out.println(ctx.channel().remoteAddress() + "->Server :" + ((ByteBuf) msg).toString(CharsetUtil.UTF_8));
        String str = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        Message message = JsonUtil.fromJson(str, Message.class);
        if ("1".equals(message.getType())) {
            //ChannelManager.put("test", ctx.channel());
            ChannelManager.login(message, ctx.channel());
        } else {

        }
        ReferenceCountUtil.release(msg);//释放
    }

    /*@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        //ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)//4冲刷所有待审消息到远程节点。关闭通道后，操作完成
               // .addListener(ChannelFutureListener.CLOSE);

    }*/

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        cause.printStackTrace(); //5打印异常堆栈跟踪
        ChannelManager.remove("test");
        ctx.close(); //6关闭通道
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                loss_connect_time++;
                System.out.println("60 秒没有接收到客户端的信息了,id=" + ctx.channel().id());
                Message message = new Message("1", "心态检测");
                ByteBuf byteBuf = Unpooled.copiedBuffer(JsonUtil.toJson(message), CharsetUtil.UTF_8);
                ctx.channel().writeAndFlush(byteBuf);
                if (loss_connect_time > 2) {
                    System.out.println("关闭这个不活跃的channel");
                    ChannelManager.remove("test");
                    ctx.channel().close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
