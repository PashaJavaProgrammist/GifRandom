package com.haretskiy.pavel.gifrandom

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

fun String.toPrettyFormat(): String {
    return try {
        val parser = JsonParser()
        val json = parser.parse(this)
                .asJsonObject
        
        val gson = GsonBuilder().setPrettyPrinting()
                .create()
        
        gson.toJson(json)
    } catch (ex: Exception) {
        this
    }
}

fun <T> LiveData<T>.distinctUntilChanged(): LiveData<T> {
    val mutableLiveData: MediatorLiveData<T> = MediatorLiveData()
    var latestValue: T? = null
    mutableLiveData.addSource(this) {
        if (latestValue != it) {
            mutableLiveData.value = it
            latestValue = it
        }
    }
    return mutableLiveData
}

fun <T, O> LiveData<T>.map(function: MapperFunction<T, O>): LiveData<O> {
    return Transformations.map(this, function)
}

typealias MapperFunction<T, O> = (T) -> O