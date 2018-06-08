package com.haretskiy.pavel.gifrandom.utils

import android.content.Context
import android.content.Intent
import com.haretskiy.pavel.gifrandom.BUNDLE_KEY_URL_DETAIL
import com.haretskiy.pavel.gifrandom.activities.DetailActivity

class RouterImpl(private var context: Context) : Router {

    override fun startDetailActivity(url: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(BUNDLE_KEY_URL_DETAIL, url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}