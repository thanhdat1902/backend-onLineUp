package com.demo.test

import org.springframework.stereotype.Service
import java.util.*

@Service
class TestService () {
    val listTest: List<String>
        get() = Arrays.asList("111", "2222")
}