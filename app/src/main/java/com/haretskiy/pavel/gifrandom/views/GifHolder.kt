package com.haretskiy.pavel.gifrandom.views

import android.support.v7.widget.RecyclerView
import com.haretskiy.pavel.gifrandom.databinding.ItemHolderBinding
import com.haretskiy.pavel.gifrandom.models.Data
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.viewModels.GifHolderViewModel


class GifHolder(private val imageLoader: ImageLoader,
                private val mItemGifBinding: ItemHolderBinding) : RecyclerView.ViewHolder(mItemGifBinding.root) {

    fun bindGif(data: Data) {
        val viewModel = GifHolderViewModel(imageLoader, data, mItemGifBinding.root.context)
        viewModel.initObservers()
        mItemGifBinding.gifViewModel = viewModel
        mItemGifBinding.executePendingBindings()
    }


}