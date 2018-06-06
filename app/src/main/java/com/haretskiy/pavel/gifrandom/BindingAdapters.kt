package com.haretskiy.pavel.gifrandom

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ImageLoaderImpl


@BindingAdapter("imageUrl"/*, "progress"*/, "imageLoader")
fun ImageView.loadImage(url: String/*, observableProgress: ObservableInt*/, imageLoader: ImageLoader) {

    imageLoader.loadImage(this, url, object : ImageLoaderImpl.LoadListener {

        override fun onLoadStarted() {
//             observableProgress.set(View.VISIBLE)
        }

        override fun onLoadFinished() {
//             observableProgress.set(View.GONE)
        }

        override fun onLoadFailed() {
//             observableProgress.set(View.GONE)
        }
    })
}