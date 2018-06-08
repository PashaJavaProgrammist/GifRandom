package com.haretskiy.pavel.gifrandom.viewModels

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ProgressController

class DetailViewModel(imageLoader: ImageLoader,
                      url: String) : ViewModel() {

    val progress: ObservableInt = ObservableInt(View.VISIBLE)
    val url = ObservableField<String>(url)
    val loader = ObservableField<ImageLoader>(imageLoader)
    val progressController: ProgressController by lazy {
        ProgressController()
    }
    val observableProgressController = ObservableField<ProgressController>(progressController)

    fun initObservers(lifecycleOwner: LifecycleOwner) {
        progressController.subscribeOnProgressChanges(lifecycleOwner, Observer {
            progress.set(if (it == true) View.VISIBLE else View.GONE)
        })
    }
}