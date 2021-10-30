package com.server.onlineup.service.provider.notification;

import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

public class MessageNotification {
    private String title;
    private String body;
    private String targetUserToken;
    private String refId;
    private String cta;

    private MessageNotification() {
    }

    public static MessageNotification Builder() {
        return new MessageNotification();
    }

    public MessageNotification setTargetUserToken(String targetUserToken) {
        this.targetUserToken = targetUserToken;
        return this;
    }

    public MessageNotification setTitle(String title) {
        this.title = title;
        return this;
    }

    public MessageNotification setBody(String body) {
        this.body = body;
        return this;
    }

    public MessageNotification setRefId(String refId) {
        this.refId = refId;
        return this;
    }

    public MessageNotification setCta(String cta) {
        this.cta = cta;
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
                .build();
    }
}
