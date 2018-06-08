package com.haretskiy.pavel.gifrandom.viewModels

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.Data
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ProgressController

class GifHolderViewModel(
        imageLoader: ImageLoader,
        data: Data,
        private val context: Context) {

    private val progressController = ProgressController()

    var progress: ObservableInt = ObservableInt(View.VISIBLE)
    var url = ObservableField<String>(data.images?.original?.url ?: EMPTY_STRING)
    var loader = ObservableField<ImageLoader>(imageLoader)
    var observableProgressController = ObservableField<ProgressController>(progressController)

    fun onItemClick(v: View) {

    }

    fun initObservers() {
        progressController.subscribeOnProgressChanges(context as LifecycleOwner, Observer {
            progress.set(if (it == true) View.VISIBLE else View.GONE)
        })
    }

}
