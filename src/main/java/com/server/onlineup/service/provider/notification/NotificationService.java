package com.server.onlineup.service.provider.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    public CompletableFuture<Void> sendAsync(Message message, OnMessagedNotification callback) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                firebaseMessaging.send(message);
                callback.onSuccess();
            } catch (FirebaseMessagingException e) {
                callback.onFail(e);
            } finally {
                return null;
            }
        });
    }

    public CompletableFuture<Void> sendAsync(Message message) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                firebaseMessaging.send(message);
            } catch (FirebaseMessagingException e) {
            } finally {
                return null;
            }
        });
    }
}
