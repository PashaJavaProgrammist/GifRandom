package com.haretskiy.pavel.gifrandom.viewModels

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.Data
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.ProgressController
import com.haretskiy.pavel.gifrandom.utils.Router

class GifHolderViewModel(
        imageLoader: ImageLoader,
        private val router: Router,
        data: Data) {

    private val progressController = ProgressController()
    private var urlStr = data.images?.original?.url ?: EMPTY_STRING

    var progress: ObservableInt = ObservableInt(View.VISIBLE)
    var url = ObservableField<String>(urlStr)
    var loader = ObservableField<ImageLoader>(imageLoader)
    var observableProgressController = ObservableField<ProgressController>(progressController)

    fun onItemClick(@Suppress("UNUSED_PARAMETER") v: View) {
        router.startDetailActivity(urlStr)
    }

    fun initObservers(lifecycleOwner: LifecycleOwner) {
        progressController.subscribeOnProgressChanges(lifecycleOwner, Observer {
            progress.set(if (it == true) View.VISIBLE else View.GONE)
        })
    }

}
