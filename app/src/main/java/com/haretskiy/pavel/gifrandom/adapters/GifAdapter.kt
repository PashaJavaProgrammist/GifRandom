package com.haretskiy.pavel.gifrandom.adapters


import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.databinding.ItemHolderBinding
import com.haretskiy.pavel.gifrandom.utils.DiffCallBack
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.Router
import com.haretskiy.pavel.gifrandom.views.GifHolder


class GifAdapter(diffCallback: DiffCallBack,
                 private var gifsList: List<String>,
                 private val imageLoader: ImageLoader,
                 private val router: Router) : PagedListAdapter<String, GifHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifHolder {
        val itemGifBinding: ItemHolderBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_holder, parent, false)
        return GifHolder(imageLoader, router, itemGifBinding)
    }

    override fun onBindViewHolder(holder: GifHolder, position: Int) {
        if (gifsList.isNotEmpty()) holder.bindGif(gifsList[position])
    }
}
