package com.haretskiy.pavel.gifrandom.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.haretskiy.pavel.gifrandom.R

class ImageLoaderImpl : ImageLoader {

    override fun loadImage(imageView: ImageView, url: String, loadListener: LoadListener) {
        loadListener.onLoadStarted()
        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions
                        .centerInsideTransform()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .placeholder(R.drawable.no_photo)
                        .error(R.drawable.error)
                )
                .listener(object : RequestListener<Drawable> {

                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        loadListener.onLoadFailed()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        loadListener.onLoadFinished()
                        return false
                    }
                })
                .into(imageView)
    }


    interface LoadListener {
        fun onLoadStarted()
        fun onLoadFinished()
        fun onLoadFailed()
    }
}