package com.haretskiy.pavel.gifrandom

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ImageLoaderImpl


@BindingAdapter("imageUrl"/*, "progressBar"*/, "imageLoader")
fun ImageView.loadImage(url: String/*, progress: ObservableInt*/, imageLoader: ImageLoader) {

    imageLoader.loadImage(this, url, object : ImageLoaderImpl.LoadListener {

        override fun onLoadStarted() {
//             progress.set(View.VISIBLE)
        }

        override fun onLoadFinished() {
//             progress.set(View.GONE)
        }

        override fun onLoadFailed() {
//             progress.set(View.GONE)
        }
    })
}