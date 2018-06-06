package com.haretskiy.pavel.gifrandom

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ImageLoaderImpl


@BindingAdapter("imageUrl", "progress", "imageLoader")
fun ImageView.loadImage(url: String, progressBar: ProgressBar, imageLoader: ImageLoader) {

    imageLoader.loadImage(this, url, object : ImageLoaderImpl.LoadListener {

        override fun onLoadStarted() {
            progressBar.visibility = View.VISIBLE
        }

        override fun onLoadFinished() {
            progressBar.visibility = View.GONE
        }

        override fun onLoadFailed() {
            progressBar.visibility = View.GONE
        }
    })
}