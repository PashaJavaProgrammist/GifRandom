package com.haretskiy.pavel.gifrandom.di

import com.google.gson.GsonBuilder
import com.haretskiy.pavel.gifrandom.BASE_URL
import com.haretskiy.pavel.gifrandom.rest.JsonLoggingInterceptor
import com.haretskiy.pavel.gifrandom.rest.RestApi
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import okhttp3.OkHttpClient
import org.koin.dsl.module.Module
import org.koin.dsl.module.applicationContext
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val restModule: Module = applicationContext {
    bean { OkHttpClient.Builder().addInterceptor(JsonLoggingInterceptor()).build() }
    bean { GsonBuilder().setLenient().create() }
    bean {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RestApi::class.java)
    }
    bean { RestApiImpl(get()) }
}


val modules = listOf(restModule)


