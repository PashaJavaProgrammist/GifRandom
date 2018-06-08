package com.haretskiy.pavel.gifrandom.viewModels

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ProgressController
import com.haretskiy.pavel.gifrandom.utils.Router

class GifHolderViewModel(
        imageLoader: ImageLoader,
        private val router: Router,
        private val urlStr: String) {

    private val progressController = ProgressController()

    val progress: ObservableInt = ObservableInt(View.VISIBLE)
    val url: ObservableField<String> by lazy {
        ObservableField<String>(urlStr)
    }
    val loader = ObservableField<ImageLoader>(imageLoader)
    val observableProgressController = ObservableField<ProgressController>(progressController)

    fun onItemClick(@Suppress("UNUSED_PARAMETER") v: View) {
        router.startDetailActivity(urlStr)
    }

    fun initObservers(lifecycleOwner: LifecycleOwner) {
        progressController.subscribeOnProgressChanges(lifecycleOwner, Observer {
            progress.set(if (it == true) View.VISIBLE else View.GONE)
        })
    }

}
