package com.haretskiy.pavel.gifrandom.adapters


import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.databinding.ItemHolderBinding
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.Router
import com.haretskiy.pavel.gifrandom.views.GifHolder


class GifAdapter(var gifsList: List<String>,
                 private val imageLoader: ImageLoader,
                 private val router: Router) : RecyclerView.Adapter<GifHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {
        val itemGifBinding: ItemHolderBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_holder, parent, false)
        return GifHolder(imageLoader, router, itemGifBinding)
    }

    override fun getItemCount() = gifsList.size

    override fun onBindViewHolder(holder: GifHolder, position: Int) {
        holder.bindGif(gifsList[position])
    }
}
