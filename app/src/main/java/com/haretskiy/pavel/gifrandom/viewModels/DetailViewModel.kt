package com.haretskiy.pavel.gifrandom.viewModels

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ProgressController

class DetailViewModel(imageLoader: ImageLoader,
                      private val progressController: ProgressController) : ViewModel() {

    var progress: ObservableInt = ObservableInt(View.VISIBLE)
    var url = ObservableField<String>(/*data.images?.original?.url ?:*/ EMPTY_STRING)
    var loader = ObservableField<ImageLoader>(imageLoader)
    var observableProgressController = ObservableField<ProgressController>(progressController)

    fun onItemClick(@Suppress("UNUSED_PARAMETER") v: View) {

    }

    fun initObservers(lifecycleOwner: LifecycleOwner) {
        progressController.subscribeOnProgressChanges(lifecycleOwner, Observer {
            progress.set(if (it == true) View.VISIBLE else View.GONE)
        })
    }
}