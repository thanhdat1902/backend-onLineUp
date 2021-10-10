package com.demo.test.model

import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "TestObj")
open class KotlinObj(
    @Id @GeneratedValue
    val id: Long = -1,
    val name: String = ""
) {
}
