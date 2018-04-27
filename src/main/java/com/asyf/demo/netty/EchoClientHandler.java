package com.asyf.demo.netty;

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
import org.apache.logging.log4j.core.util.UuidUtil;
import org.bson.codecs.UuidCodecProvider;

import java.util.Date;
import java.util.UUID;

@ChannelHandler.Sharable //1@Sharable  标记这个类的实例可以在 channel 里共享
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    private static final int TRY_TIMES = 300;
    private int currentTime = 0;
    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Heartbeat心跳",
            CharsetUtil.UTF_8));


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("激活时间是：" + new Date());
        System.out.println("HeartBeatClientHandler channelActive");
        //激活的时候发送登录请求
        boolean a = login(ctx.channel());
        ctx.fireChannelActive();
    }

    private boolean login(Channel channel) {
        Message message = new Message();
        message.setUserId(String.valueOf(UUID.randomUUID()));
        message.setToken("testtoken");
        message.setAppKey("testappkey");
        message.setGroup("group000");
        message.setAlias("alias000");
        message.setType("1");
        ByteBuf byteBuf = Unpooled.copiedBuffer(JsonUtil.toJson(message), CharsetUtil.UTF_8);
        channel.writeAndFlush(byteBuf);
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("停止时间是：" + new Date());
        System.out.println("HeartBeatClientHandler channelInactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,
                                Throwable cause) {
        //4记录日志错误并关闭 channel
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("循环触发时间：" + new Date());
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                if (currentTime <= TRY_TIMES) {
                    System.out.println("currentTime:" + currentTime);
                    currentTime++;
                    Message message = new Message("0", "类型是1");
                    ByteBuf byteBuf = Unpooled.copiedBuffer(JsonUtil.toJson(message), CharsetUtil.UTF_8);
                    ctx.channel().writeAndFlush(byteBuf);
                    //ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
                }
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String str = ((ByteBuf) msg).toString(CharsetUtil.UTF_8);
        Message message = JsonUtil.fromJson(str, Message.class);
        System.out.println(message);
        if (message.getType().equals("1")) {
            //ctx.write("has read message from server....." + message.toString());
            //ctx.flush();
            System.out.println("登录结果：" + message.toString());
        } else {
            System.err.println("收到了未知类型的数据");
        }
        ReferenceCountUtil.release(msg);//释放
    }
}
