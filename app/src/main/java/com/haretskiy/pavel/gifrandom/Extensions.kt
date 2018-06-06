package com.haretskiy.pavel.gifrandom

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

fun String.toPrettyFormat(): String {
    return try {
        val parser = JsonParser()
        val json = parser.parse(this).asJsonObject

        val gson = GsonBuilder().setPrettyPrinting().create()

        gson.toJson(json)
    } catch (ex: Exception) {
        this
    }
}

