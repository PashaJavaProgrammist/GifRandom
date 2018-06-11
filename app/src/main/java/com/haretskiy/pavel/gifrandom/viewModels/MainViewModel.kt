package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.LIMIT
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsSourceFactory
import java.util.concurrent.Executors


class MainViewModel(context: Application,
                    factory: GifsSourceFactory) : AndroidViewModel(context) {

    private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(LIMIT)
            .build()

    val pagedListLiveData = LivePagedListBuilder(factory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()

    val searchWord: ObservableField<String> = ObservableField()
    val ratingSelectedPos = ObservableInt(0)
    val progress = ObservableInt(View.GONE)

    fun onClickSearch(@Suppress("UNUSED_PARAMETER") v: View) {
//        todo: need to search by word
    }

}

