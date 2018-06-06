package com.haretskiy.pavel.gifrandom

import android.databinding.BindingAdapter
import android.util.Log
import android.widget.ImageView


@BindingAdapter("imageUrl"/*, "progressBar", "imageLoader"*/)
fun ImageView.loadImage(url: String/*, progress: ObservableInt, imageLoader: ImageLoader*/) {

    Log.d("BindingAdapter", "BindingAdapter $url")
    /* imageLoader.loadImage(this, url, object : ImageLoaderImpl.LoadListener {

         override fun onLoadStarted() {
             progress.set(View.VISIBLE)
         }

         override fun onLoadFinished() {
             progress.set(View.GONE)
         }

         override fun onLoadFailed() {
             progress.set(View.GONE)
         }
     })*/
}