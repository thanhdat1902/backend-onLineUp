package com.server.onlineup.controller;

import com.server.onlineup.common.utils.TimeUtils;
import com.server.onlineup.model.entity.JavaObj;
import com.server.onlineup.model.entity.ProfileEntity;
import com.server.onlineup.repository.JavaRepository;
import com.server.onlineup.repository.ProfileRepository;
import com.server.onlineup.service.provider.notification.MessageNotification;
import com.server.onlineup.service.provider.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@Controller
@CrossOrigin
@RequestMapping(path = "/test")
public class JavaController {
    @Autowired
    private JavaRepository userRepository;

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    private NotificationService noti;

    @PostMapping(value = "/sync")
    public @ResponseBody
    long test() {
//        JavaObj obj = new JavaObj();
//        obj.setName("Test 1");
//        long startTime = TimeUtils.getCurrentTimestamp();
//        userRepository.save(obj);
//
//        Long size = Long.valueOf(userRepository.findAll().size());
//
//        System.out.println(TimeUtils.getCurrentTimestamp() - startTime + "ms");
//        return size;
//        return computeVeryLong();

        Optional<ProfileEntity> profile = profileRepository.findByEmail("lamnguyen5464@gmail.com");

        noti.saveAndSendAsync(MessageNotification
                .Builder()
                .setTitle("TTTTTitle")
                .setBody("This is content aergaga " + TimeUtils.getCurrentTimestamp())
                .setExtra("field 1", 1111)
                .setExtra("field 2", "222")
                .setTargetUserToken(profile.get())
        ).thenCompose((res) -> {
            System.out.println(("done!!"));
            return null;
        });

        System.out.println("out here");

        return 0;

    }

    @PostMapping(value = "/async")
    public @ResponseBody
    CompletableFuture<Long> testAsync() {
        long startTime = TimeUtils.getCurrentTimestamp();

        JavaObj obj = new JavaObj();
        obj.setName("Test 1");

        return CompletableFuture
                .supplyAsync(() -> {
                    userRepository.saveAsync(obj).join();
                    return 0;   //dummy return value
                })
                .thenApply((ignoredVoid) -> {

                    System.out.println(TimeUtils.getCurrentTimestamp() - startTime + "ms");
                    return userRepository.findAllAsync()
                            .thenApply((res) -> Long.valueOf(res.size()));
                })
                .join();

//        CompletableFuture
//                .supplyAsync(() -> null)
//                .thenApply((res) -> task1().join())
//                .thenApply((res2) -> task2().join())
//                .thenApply((res3) -> task1().join());


    }

    private CompletableFuture<Object> task1() {
        return supplyAsync(new Supplier<Object>() {
            @Override
            public Long get() {
                System.out.println("11111");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("return 1");
                    return Long.valueOf(111);
                }
            }
        });
    }

    private CompletableFuture<Object> task2() {
        return supplyAsync(() -> {
            System.out.println("2222");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("return 2");
                return Long.valueOf(222);
            }
        });
    }

    private Collection<JavaObj> computeVeryLong() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            return Arrays.asList(new JavaObj());
        }

//        Collection<JavaObj> list = userRepository.findAll();
//        return list;
    }

    @Async
    private CompletableFuture<Collection<JavaObj>> computeVeryLongAsync() {
        return supplyAsync(new Supplier<Collection<JavaObj>>() {
            @Override
            public Collection<JavaObj> get() {
                return computeVeryLong();
            }
        }, Executors.newCachedThreadPool());
    }


}