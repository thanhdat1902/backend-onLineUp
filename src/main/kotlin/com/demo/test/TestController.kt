package com.demo.test

import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping("/test")
class TestController(private var testService: TestService) {

    @GetMapping
    fun testGet(): String {
        return "You are calling api get"
    }

    @PostMapping
    fun getList(@RequestBody req: TestObj): List<TestObj>{
        println(req.getId())
        println(req.getName())
        return Arrays.asList(req)
    }
}