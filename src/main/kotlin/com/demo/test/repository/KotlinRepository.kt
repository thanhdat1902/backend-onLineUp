package com.demo.test.repository

import com.demo.test.model.KotlinObj
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface KotlinRepository : CrudRepository<KotlinObj, Int> {
}