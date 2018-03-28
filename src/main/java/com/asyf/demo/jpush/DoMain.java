package com.asyf.demo.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class DoMain {
    private static final Logger LOG = LoggerFactory.getLogger(DoMain.class);
    private static String MASTER_SECRET = "60d9654978a0111556de8734";
    private static String APP_KEY = "4e6f9f8ecd6d6940f6efe4d2";
    private static final String ALERT = "新消息";
    private static final String TITLE = "标题111sssss";

    public static void main(String[] args) {
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());

        // For push, all you need do is to build PushPayload object.
        Map<String, String> extras = new HashMap<>();
        extras.put("type", "100");
        // PushPayload pushPayload = buildPushObject_all_all_alert("title_1", "content_msg", extras);
        PushPayload pushPayload = buildPushObject_android_tag_alertWithTitle();

        try {
            PushResult result = jpushClient.sendPush(pushPayload);
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            // Connection error, should retry later
            LOG.error("Connection error, should retry later", e);

        } catch (APIRequestException e) {
            // Should review the error, and fix the request
            LOG.error("Should review the error, and fix the request", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
        }
    }

    //平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE。
    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("asyf_group"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }

    private static PushPayload buildPushObject_all_all_alert(String title, String content, Map<String, String> extras) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())// 设置接受的平台
                .setAudience(Audience.all())// Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
                .setNotification(
                        Notification.newBuilder()
                                .addPlatformNotification(IosNotification.newBuilder().addExtras(extras).setAlert(content).build())
                                .addPlatformNotification(AndroidNotification.newBuilder().addExtras(extras).setAlert(content).setTitle(title).build())
                                .build()
                )
                .build();
    }

}
