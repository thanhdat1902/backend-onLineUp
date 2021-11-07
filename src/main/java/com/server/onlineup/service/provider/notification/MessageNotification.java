package com.server.onlineup.service.provider.notification;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import net.minidev.json.JSONObject;

public class MessageNotification {
    private String title;
    private String body;
    private JSONObject extraData;
    private String targetUserToken;

    private MessageNotification() {
    }

    public static MessageNotification Builder() {
        MessageNotification instance = new MessageNotification();
        return instance;
    }

    public MessageNotification setTargetUserToken(String targetUserToken) {
        this.targetUserToken = targetUserToken;
        return this;
    }

    public MessageNotification setBody(String body) {
        this.body = body;
        return this;
    }

    public MessageNotification setTitle(String title) {
        this.title = title;
        return this;
    }

    public MessageNotification setExtra(String field, Object value) {
        if (this.extraData == null) {
            this.extraData = new JSONObject();
        }
        this.extraData.appendField(field, value);
        return this;
    }

    public Message build() {
        Notification notification = Notification
                .builder()
                .setTitle(this.title)
                .setBody(this.body)
                .build();

        return Message
                .builder()
                .setToken(this.targetUserToken)
                .setNotification(notification)
                .putData(KeyNotification.extra, this.extraData.toJSONString())
                .putData(KeyNotification.text, this.extraData.toJSONString())
                .build();
    }
}
