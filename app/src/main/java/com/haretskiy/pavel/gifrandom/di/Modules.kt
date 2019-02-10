package com.haretskiy.pavel.gifrandom.di

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import com.haretskiy.pavel.gifrandom.BASE_URL
import com.haretskiy.pavel.gifrandom.adapters.GifAdapter
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.data.RepositoryImpl
import com.haretskiy.pavel.gifrandom.rest.JsonInterceptor
import com.haretskiy.pavel.gifrandom.rest.RestApi
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import com.haretskiy.pavel.gifrandom.utils.*
import com.haretskiy.pavel.gifrandom.utils.pagging.DiffCallBack
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsSourceFactory
import com.haretskiy.pavel.gifrandom.viewModels.DetailViewModel
import com.haretskiy.pavel.gifrandom.viewModels.MainViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.Module
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val restModule: Module = module {
    single {
        OkHttpClient.Builder()
                .addInterceptor(JsonInterceptor())
                .build()
    }
    single {
        GsonBuilder().setLenient()
                .create()
    }
    single {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(get())
                .addConverterFactory(GsonConverterFactory.create(get()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(RestApi::class.java)
    }
    single { RestApiImpl(get()) }
    
    single { Connectivity(androidApplication(), get()) }
    
    factory { androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
}

val appModule: Module = module {
    single { Toaster(androidApplication()) }
    single { ImageLoaderImpl() as ImageLoader }
    single { RouterImpl(androidApplication()) as Router }
    single { RepositoryImpl(get()) as Repository }
    single { DiffCallBack() }
    single { GifsSourceFactory(get()) }
    factory { GifAdapter(get(), get(), get()) }
}

val viewModelModule: Module = module {
    viewModel { MainViewModel(androidApplication(), get(), get()) }
    viewModel { parameterList ->
        DetailViewModel(get(), parameterList[0])
    }
}

val modules = listOf(restModule, appModule, viewModelModule)

