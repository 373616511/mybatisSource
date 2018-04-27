package com.asyf.demo.netty;

import com.asyf.demo.mongodb.MongoDBUtil;
import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.util.CharsetUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ChannelManager {
    private static final Map<String, Channel> channelMap = new HashMap<>();
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private ChannelManager() {
    }

    public static void put(String key, Channel channel) {
        System.out.println("添加channel");
        channelMap.put(key, channel);
    }

    public static Channel get(String key) {
        return channelMap.get(key);
    }

    public static void remove(String key) {
        channelMap.remove(key);
    }

    public static void push(String id, Message message) {
        MongoCollection<Document> collection = MongoDBUtil.instance.getCollection("test", "netty_user");
        Document byId = MongoDBUtil.instance.findById(collection, id);
        if (byId != null) {
            String channelId = (String) byId.get("channelId");
            Channel channel = get(channelId);
            if (channel != null) {
                ByteBuf byteBuf = Unpooled.copiedBuffer(JsonUtil.toJson(message), CharsetUtil.UTF_8);
                channel.writeAndFlush(byteBuf);
            }
        }
    }

    private static void push(Channel channel, Message message) {
        ByteBuf byteBuf = Unpooled.copiedBuffer(JsonUtil.toJson(message), CharsetUtil.UTF_8);
        channel.writeAndFlush(byteBuf);
    }

    public static boolean login(Message message, Channel channel) {
        Message result = new Message();
        String errMsg = null;
        if (message == null) {
            errMsg = "登录信息不全";
        }
        if (errMsg == null && StringUtils.isBlank(message.getUserId())) {
            errMsg = "缺少userId";
        }
        if (errMsg == null && (StringUtils.isBlank(message.getToken()) || StringUtils.isBlank(message.getAppKey()))) {
            errMsg = "appKey或token缺失";
        }
        if (errMsg != null) {
            result.setType("1");
            result.setMsg(errMsg);
            push(channel, result);
            return false;
        }
        boolean checkMessage = checkMessage(message);
        if (checkMessage) {
            result.setType("1");
            result.setMsg("success");
            result.setMsgCode("1000");
            //存储用户消息
            saveUser(message, channel);
            //存储channel
            put(String.valueOf(channel.id()), channel);
            //反馈消息给客户端
            push(channel, result);
        } else {
            result.setType("1");
            result.setMsg("error登录失败");
            result.setMsgCode("1001");
            push(channel, result);
            return false;
        }
        return true;
    }

    private static void saveUser(Message message, Channel channel) {
        MongoCollection<Document> collection = MongoDBUtil.instance.getCollection("test", "netty_user");
        UserInfo u = new UserInfo();
        u.setId(message.getUserId());
        u.setFirstLoginDate(DateFormatUtils.format(new Date(), DATE_PATTERN));
        u.setLastLoginDate(DateFormatUtils.format(new Date(), DATE_PATTERN));
        u.setStatus("1");
        u.setAlias(message.getAlias());
        u.setGroup(message.getGroup());
        u.setToken(message.getToken());
        u.setAppKey(message.getAppKey());
        u.setChannelId(String.valueOf(channel.id()));
        Document document = JsonUtil.fromJson(JsonUtil.toJson(u), Document.class);
        //检测id是否存在，存在则更新
        Document byId = MongoDBUtil.instance.findById(collection, u.getId());
        if (byId != null) {
            MongoDBUtil.instance.updateById(collection, u.getId(), document);
        } else {
            //保存
            MongoDBUtil.instance.insertOne(collection, document);
        }
    }

    /**
     * 登录检测message，检查是否有效
     *
     * @param message
     * @return
     */
    private static boolean checkMessage(Message message) {
        //TODO 检测代码 测试-不检测
        return true;
    }

}
