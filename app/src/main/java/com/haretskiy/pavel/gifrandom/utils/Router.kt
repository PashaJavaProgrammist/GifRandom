package com.haretskiy.pavel.gifrandom.utils

import android.content.Context
import android.view.View

interface Router {

    fun startDetailActivity(url: String)

    fun startDetailActivity(context: Context, imageView: View, url: String)
}