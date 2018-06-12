package com.haretskiy.pavel.gifrandom.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.view.View
import com.haretskiy.pavel.gifrandom.BUNDLE_KEY_URL_DETAIL
import com.haretskiy.pavel.gifrandom.SHARE_TYPE_TEXT
import com.haretskiy.pavel.gifrandom.VIEW_NAME_IMAGE
import com.haretskiy.pavel.gifrandom.activities.DetailActivity

class RouterImpl(private var context: Context) : Router {

    override fun startDetailActivity(url: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(BUNDLE_KEY_URL_DETAIL, url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    override fun startDetailActivity(context: Context, imageView: View, url: String) {

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(BUNDLE_KEY_URL_DETAIL, url)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                context as Activity,
                Pair<View, String>(imageView, VIEW_NAME_IMAGE))

        context.startActivity(intent, activityOptions.toBundle())
    }

    override fun shareGif(urlStr: String) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        shareIntent.type = SHARE_TYPE_TEXT
        shareIntent.putExtra(Intent.EXTRA_TEXT, urlStr)

        context.startActivity(shareIntent)
    }
}