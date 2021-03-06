package com.haretskiy.pavel.gifrandom

import android.app.Application
import com.haretskiy.pavel.gifrandom.di.modules
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, modules)
    }

}