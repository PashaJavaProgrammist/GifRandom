package com.haretskiy.pavel.gifrandom

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ImageLoaderImpl
import com.haretskiy.pavel.gifrandom.utils.ProgressController


@BindingAdapter("imageUrl", "progress", "imageLoader")
fun ImageView.loadImage(url: String, progressController: ProgressController, imageLoader: ImageLoader) {

    imageLoader.loadImage(this, url, object : ImageLoaderImpl.LoadListener {

        override fun onLoadStarted() {
            progressController.setProgressVisibility(true)
        }

        override fun onLoadFinished() {
            progressController.setProgressVisibility(false)
        }

        override fun onLoadFailed() {
            progressController.setProgressVisibility(false)
        }
    })
}