package com.asyf.demo.netty;

public class UserInfo {

    private static final String DEFAULT_ALIAS = "";
    private static final String DEFAULT_GROUP = "";
    private static final String DEFAULT_STATUS_OFFLINE = "0";
    private static final String DEFAULT_STATUS_ONLINE = "1";

    private String userId;//用户id,安卓app首次启动时生成的uuid
    private String firstLoginDate;//首次登录时间
    private String lastLoginDate;//最后登录时间
    private String status;//用户状态
    private String alias;//别名
    private String group;//分组
    private String token;//秘钥
    private String appKey;//appKey用来区分app
    private String channelId;//此用户长连接的channelId，用户每次重新登录修改channelId

    public UserInfo(String userId, String date, String alias, String group, String token, String appKey, String channelId) {
        this.userId = userId;
        this.firstLoginDate = date;
        this.lastLoginDate = date;
        this.status = DEFAULT_STATUS_ONLINE;
        this.alias = alias;
        this.group = group;
        this.token = token;
        this.appKey = appKey;
        this.channelId = channelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstLoginDate() {
        return firstLoginDate;
    }

    public void setFirstLoginDate(String firstLoginDate) {
        this.firstLoginDate = firstLoginDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
