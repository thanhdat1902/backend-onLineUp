package com.server.onlineup.repository;

import com.server.onlineup.model.entity.UniversalLinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

public interface UniversalLinkRepository extends JpaRepository<UniversalLinkEntity, String> {
    @Async
    @Transactional
    @Query(
            value = "select * from universal_link where id = :#{#code}",
            nativeQuery = true)
    CompletableFuture<UniversalLinkEntity> findByCodeAsync(String code);

    @Async
    @Transactional
    @Modifying
    @Query(
            value = "insert into universal_link(id, params, created_time) VALUES (:#{#obj.id}, :#{#obj.params}, :#{#obj.created_time})",
            nativeQuery = true
    )
    CompletableFuture<Void> saveAsync(@Param("obj") UniversalLinkEntity obj);
}
