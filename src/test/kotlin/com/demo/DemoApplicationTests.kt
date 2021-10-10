package com.demo

import demo.test.DemoApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes=[DemoApplication::class])
class DemoApplicationTests {

	@Test
	fun contextLoads() {
		println("ahaer")
	}

}
