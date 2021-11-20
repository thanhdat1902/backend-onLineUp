package com.server.onlineup.model.entity;

import com.server.onlineup.common.utils.TimeUtils;
import com.server.onlineup.service.provider.notification.MessageNotification;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "notification")
public class NotificationEntity {
    @Id
    public String id;

    @Column(nullable = false)
    public String user; //email
    public String title;
    public String body;
    public long created_time;
    public boolean seen;
    public String extra;

    public NotificationEntity(MessageNotification notification) {
        this.id = UUID.randomUUID().toString();
        this.created_time = TimeUtils.getCurrentTimestamp();
        this.seen = false;
        this.title = notification.getTitle();
        this.body = notification.getBody();
        this.extra = notification.getStringExtra();

        if (notification.getTargetUser() != null) {
            this.user = notification.getTargetUser().getEmail();
        }
    }
}
