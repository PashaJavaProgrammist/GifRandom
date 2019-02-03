package com.haretskiy.pavel.gifrandom.utils

import android.content.Context
import android.widget.Toast

class Toaster(private val context: Context) {

    fun showToast(message: String, isLong: Boolean = false) {
        Toast.makeText(context, message, if (isLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT).show()
    }
}