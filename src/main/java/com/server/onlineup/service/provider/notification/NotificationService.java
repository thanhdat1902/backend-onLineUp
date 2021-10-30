package com.server.onlineup.service.provider.notification;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NotificationService {
    @Bean
    FirebaseMessaging getFirebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("firebase-service-account.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "onLineUp-server");
        return FirebaseMessaging.getInstance(app);
    }

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
