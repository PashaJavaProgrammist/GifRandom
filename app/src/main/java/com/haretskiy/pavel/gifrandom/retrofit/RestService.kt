package com.haretskiy.pavel.gifrandom.retrofit

import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import com.google.gson.GsonBuilder
import com.haretskiy.pavel.gifrandom.BASEURL
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

fun getRestApi(): IRestService {
    val gson = GsonBuilder().setLenient().create()
    val retrofit = Retrofit.Builder()
            .baseUrl(BASEURL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    return retrofit.create(IRestService::class.java)
}
