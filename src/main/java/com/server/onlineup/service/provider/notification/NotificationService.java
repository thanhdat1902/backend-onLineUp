package com.server.onlineup.service.provider.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public void sendNotification(Message message, OnMessagedNotification callback) {
        try {
            firebaseMessaging.send(message);
            callback.onSuccess();
        } catch (FirebaseMessagingException e) {
            callback.onFail(e);
        }
    }
}
