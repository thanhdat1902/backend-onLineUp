package com.server.onlineup.service.provider.notification;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.server.onlineup.model.entity.ProfileEntity;
import net.minidev.json.JSONObject;

public class MessageNotification {
    private String title;
    private String body;
    private JSONObject extraData;
    private ProfileEntity targetUser;

    private MessageNotification() {
    }

    public static MessageNotification Builder() {
        MessageNotification instance = new MessageNotification();
        return instance;
    }

    public MessageNotification setTargetUserToken(ProfileEntity targetUser) {
        this.targetUser = targetUser;
        return this;
    }

    public ProfileEntity getTargetUser() {
        return this.targetUser;
    }

    public MessageNotification setBody(String body) {
        this.body = body;
        return this;
    }

    public String getBody() {
        return this.body;
    }

    public MessageNotification setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public MessageNotification setExtra(String field, Object value) {
        if (this.extraData == null) {
            this.extraData = new JSONObject();
        }
        this.extraData.appendField(field, value);
        return this;
    }

    public String getStringExtra() {
        return this.extraData == null ? "" : this.extraData.toJSONString();
    }

    public Message build() {
        Notification notification = Notification
                .builder()
                .setTitle(this.title)
                .setBody(this.body)
                .build();

        return Message
                .builder()
                .setToken(this.targetUser != null ? this.targetUser.getFcmToken() : null)
                .setNotification(notification)
                .putData(KeyNotification.extra, this.getStringExtra())
                .build();
    }
}
