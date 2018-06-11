package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.LIMIT
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsSourceFactory
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsTrendingDataSource
import java.util.concurrent.Executors


class MainViewModel(private val context: Application,
                    factory: GifsSourceFactory) : AndroidViewModel(context) {

    private val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(LIMIT)
            .build()

    val pagedListLiveData = LivePagedListBuilder(
            factory.initCallback(object : GifsTrendingDataSource.GifsLoadedCallback {
                override fun onStartInitialLoad() {
                    progress.set(View.VISIBLE)
                }

                override fun onFinishInitialLoad() {
                    progress.set(View.GONE)
                }
            }), config).setFetchExecutor(Executors.newSingleThreadExecutor()).build()

    val searchWord: ObservableField<String> = ObservableField()
    val ratingSelectedPos = ObservableInt(0)
    val progress = ObservableInt(View.VISIBLE)

    private fun getCurrentRating(): String {
        val ratings = context.resources.getStringArray(R.array.ratings)
        return ratings[ratingSelectedPos.get()]
    }

    fun onClickSearch(@Suppress("UNUSED_PARAMETER") v: View) {
//        todo: need to search by word
    }

}

