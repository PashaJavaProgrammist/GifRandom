package com.haretskiy.pavel.gifrandom.di

import com.google.gson.GsonBuilder
import com.haretskiy.pavel.gifrandom.BASE_URL
import com.haretskiy.pavel.gifrandom.BUNDLE_KEY_URL
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.data.RepositoryImpl
import com.haretskiy.pavel.gifrandom.data.Storage
import com.haretskiy.pavel.gifrandom.data.StorageImpl
import com.haretskiy.pavel.gifrandom.rest.JsonLoggingInterceptor
import com.haretskiy.pavel.gifrandom.rest.RestApi
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import com.haretskiy.pavel.gifrandom.utils.*
import com.haretskiy.pavel.gifrandom.viewModels.DetailViewModel
import com.haretskiy.pavel.gifrandom.viewModels.MainViewModel
import okhttp3.OkHttpClient
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.context.ParameterProvider
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

val appModule: Module = applicationContext {
    bean { Toaster(androidApplication()) }
    bean { ImageLoaderImpl() as ImageLoader }
    bean { RouterImpl(androidApplication()) as Router }
    bean { StorageImpl() as Storage }
    bean { RepositoryImpl(get(), get()) as Repository }
}

val viewModelModel: Module = applicationContext {
    viewModel { MainViewModel(androidApplication(), get(), get()) }
    viewModel { params: ParameterProvider ->
        DetailViewModel(get(), params[BUNDLE_KEY_URL])
    }
}


val modules = listOf(restModule, appModule, viewModelModel)


