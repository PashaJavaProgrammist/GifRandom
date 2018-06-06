package com.haretskiy.pavel.gifrandom.viewModels

import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.Data
import com.haretskiy.pavel.gifrandom.utils.ImageLoader


class GifHolderViewModel(imageLoader: ImageLoader, private val data: Data) {

    var progress: ObservableInt = ObservableInt(View.VISIBLE)
    var observableProgressBar = ObservableField<ObservableInt>(progress)
    var url = ObservableField<String>(data.images?.original?.url ?: EMPTY_STRING)
    var loader = ObservableField<ImageLoader>(imageLoader)

    fun onItemClick(v: View) {

    }

}
