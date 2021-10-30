package com.server.onlineup.service.provider.notification;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface OnMessagedNotification {
    void onSuccess();

    void onFail(FirebaseMessagingException exception);
}
