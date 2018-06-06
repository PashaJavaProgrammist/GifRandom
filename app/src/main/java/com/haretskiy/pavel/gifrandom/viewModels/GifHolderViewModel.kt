package com.haretskiy.pavel.gifrandom.viewModels

import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import android.widget.ImageView
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.Data
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ImageLoaderImpl


class GifHolderViewModel(imageLoader: ImageLoader, private val data: Data) {

    var progress = ObservableBoolean(true)
    var progressBar = ObservableField<ObservableBoolean>(progress)
    var url = ObservableField<String>(data.images?.original?.url ?: EMPTY_STRING)
    var loader = ObservableField<ImageLoader>(imageLoader)

    fun onItemClick(v: View) {

    }
}

@BindingAdapter("imageUrl", "progressBar", "imageLoader")
fun ImageView.setImageUrl(url: String, progress: ObservableBoolean, imageLoader: ImageLoader) {

    imageLoader.loadImage(this, url, object : ImageLoaderImpl.LoadListener {

        override fun onLoadStarted() {
            progress.set(true)
        }

        override fun onLoadFinished() {
            progress.set(false)
        }

        override fun onLoadFailed() {
            progress.set(false)
        }
    })
}