package com.server.onlineup.repository;

import com.server.onlineup.model.entity.JavaObj;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

public interface JavaRepository extends JpaRepository<JavaObj, Long> {
    @Query(
            value = "select * from test_obj_2 where id < 15",
            nativeQuery = true)
    Collection<JavaObj> findCustom();

    @Query(
            value = "select * from test_obj_2 where id < 15",
            nativeQuery = true)
    Collection<JavaObj> findA();

    @Async
    @Transactional
    @Query(
            value = "select * from test_obj_2",
            nativeQuery = true)
    CompletableFuture<Collection<JavaObj>> findAllAsync();

    @Async
    @Transactional
    @Modifying
    @Query(
            value = "insert into test_obj_2(id, name) VALUES (:#{#obj.id}, :#{#obj.name})",
            nativeQuery = true
    )
    CompletableFuture<Void> saveAsync(@Param("obj") JavaObj obj);
}
