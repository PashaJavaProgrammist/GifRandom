package com.haretskiy.pavel.gifrandom.utils.pagging

import android.arch.paging.PositionalDataSource
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.data.RepositoryImpl

class GifsTrendingDataSource(
        private val repository: Repository,
        var gifsLoadedCallback: GifsLoadedCallback,
        private var rating: String) : PositionalDataSource<String>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<String>) {
        gifsLoadedCallback.onStartPageLoad()
        repository.loadTrendingGifs(rating, params.startPosition.toString(), object : RepositoryImpl.ResultCallback {
            override fun onResult(list: List<String>) {
                callback.onResult(list)
                gifsLoadedCallback.onFinishPageLoad()
            }
        })
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<String>) {
        gifsLoadedCallback.onStartInitialLoad()
        repository.loadTrendingGifs(rating, ZERO_OFFSET, object : RepositoryImpl.ResultCallback {
            override fun onResult(list: List<String>) {
                callback.onResult(list, params.requestedStartPosition)
                gifsLoadedCallback.onFinishInitialLoad()
            }
        })
    }

    interface GifsLoadedCallback {
        fun onStartInitialLoad() {}
        fun onFinishInitialLoad() {}
        fun onStartPageLoad() {}
        fun onFinishPageLoad() {}
    }
}