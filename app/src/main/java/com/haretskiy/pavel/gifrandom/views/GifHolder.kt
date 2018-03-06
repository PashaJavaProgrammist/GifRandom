package com.haretskiy.pavel.gifrandom.views

import android.support.v7.widget.RecyclerView
import com.haretskiy.pavel.gifrandom.databinding.ItemHolderBinding
import com.haretskiy.pavel.gifrandom.models.GifResponse


class GifHolder(var mItemGifBinding: ItemHolderBinding) : RecyclerView.ViewHolder(mItemGifBinding.itemGif) {

    fun bindGif(gifResult: GifResponse) {

    }
}