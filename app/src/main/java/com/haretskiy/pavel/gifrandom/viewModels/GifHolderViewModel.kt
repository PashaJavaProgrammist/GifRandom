package com.haretskiy.pavel.gifrandom.viewModels

import android.databinding.BindingAdapter
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import android.view.View
import android.widget.ImageView
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ImageLoaderImpl


class GifHolderViewModel(private val imageLoader: ImageLoader) {

    val progress = ObservableBoolean(true)
    val url = ObservableField<String>(EMPTY_STRING)

    fun onItemClick(v: View) {

    }

    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, url: String) {
        imageLoader.loadImage(imageView, url, object : ImageLoaderImpl.LoadListener {

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
}