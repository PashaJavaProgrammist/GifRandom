package com.haretskiy.pavel.gifrandom.utils

import android.widget.ImageView

interface ImageLoader {

    fun loadImage(imageView: ImageView, url: String, loadListener: ImageLoaderImpl.LoadListener)
}
