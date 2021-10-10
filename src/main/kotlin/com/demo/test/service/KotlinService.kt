package com.demo.test.service

import com.demo.test.repository.KotlinRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KotlinService {

    @Autowired
    lateinit var demoRepository: KotlinRepository

//    fun getListTest(): MutableList<TestObj>{
////        return testRepository.count()
//        return listOf<TestObj>()
//    }


    fun getCount(): Long {
        return 0
        return demoRepository.count()
    }
}