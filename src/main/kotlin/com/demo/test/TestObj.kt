package com.demo.test

import com.fasterxml.jackson.annotation.JsonProperty

class TestObj(
    @JsonProperty("id")
    private var id: String,
    @JsonProperty("name")
    private var name: String
) {

    fun getId(): String {
        return this.id
    }

    fun getName(): String{
        return this.name
    }

}