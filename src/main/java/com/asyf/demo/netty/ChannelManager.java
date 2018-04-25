package com.asyf.demo.netty;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class ChannelManager {
    private static final Map<String, Channel> channelMap = new HashMap<>();

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

    public static void push(String key, Message message) {
        Channel channel = get(key);
        channel.writeAndFlush(message);
    }

}
