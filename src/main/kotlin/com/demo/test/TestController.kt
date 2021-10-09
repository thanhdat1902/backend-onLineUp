package com.demo.test

import demo.test.JavaTest
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/test")
class TestController(private var testService: TestService) {

    @GetMapping
    fun testGet(): String {
        return "You are calling api get"
    }

//    @PostMapping
//    fun getList(@RequestBody req: TestObj): List<TestObj>{
//        println(req.getId())
//        println(req.getName())
//        println(JavaTest.get())
//
//        return Arrays.asList(req)
//    }

    @PostMapping
    fun testPost(): List<String>{
        return Arrays.asList("1111","222")
    }
}