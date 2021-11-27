package com.server.onlineup.repository;

import com.server.onlineup.model.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

public interface NotificationRepository extends JpaRepository<NotificationEntity, String> {
    @Async
    @Transactional
    @Modifying
    @Query(
            value = "insert into notification(id, user, title, body, created_time, seen, extra) VALUES (:#{#noti.id}, :#{#noti.user}, :#{#noti.title}, :#{#noti.body}, :#{#noti.created_time}, :#{#noti.seen}, :#{#noti.extra})",
            nativeQuery = true
    )
    CompletableFuture<Void> saveAsync(@Param("noti") NotificationEntity notificationEntity);
}
