package com.server.onlineup.service.provider.notification;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.server.onlineup.model.entity.NotificationEntity;
import com.server.onlineup.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class NotificationService {
    @Autowired
    private FirebaseMessaging firebaseMessaging;

    @Autowired
    private NotificationRepository notificationRepository;

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

    public CompletableFuture<Void> saveAsync(NotificationEntity notificationEntity) {
        return CompletableFuture.supplyAsync(() -> {
            notificationRepository.saveAsync(notificationEntity).join();
            return null;
        });
    }

    public CompletableFuture<Void> saveAndSendAsync(MessageNotification messageNotification) {
        CompletableFuture<Void> saveTask = saveAsync(new NotificationEntity(messageNotification));
        CompletableFuture<Void> sendTask = sendAsync(messageNotification.build());
        return CompletableFuture.allOf(saveTask, sendTask);
    }
}
