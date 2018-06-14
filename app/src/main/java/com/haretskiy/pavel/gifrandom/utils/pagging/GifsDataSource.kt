package com.haretskiy.pavel.gifrandom.utils.pagging

import android.arch.paging.PositionalDataSource
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.data.RepositoryImpl.ResultCallback

class GifsDataSource(
        private val repository: Repository,
        private var gifsLoadedCallback: GifsLoadedCallback,
        private var rating: String,
        private var word: String) : PositionalDataSource<String>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<String>) {
        gifsLoadedCallback.onStartPageLoad()
        when {
            word.isEmpty() -> repository.loadTrendingGifs(rating, params.startPosition.toString(), resultCallback(callback))
            else -> repository.loadGifsByWord(word, rating, params.startPosition.toString(), resultCallback(callback))
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<String>) {
        gifsLoadedCallback.onStartInitialLoad()
        when {
            word.isEmpty() -> repository.loadTrendingGifs(rating, ZERO_OFFSET, initialResultCallback(params, callback))
            else -> repository.loadGifsByWord(word, rating, ZERO_OFFSET, initialResultCallback(params, callback))
        }
    }

    private fun initialResultCallback(params: LoadInitialParams, callback: LoadInitialCallback<String>) = object : ResultCallback {
        override fun onResult(list: List<String>) = try {
            var startPos = params.requestedStartPosition
            when {
                startPos < 0 -> startPos = 0
            }
            callback.onResult(list, startPos)
            gifsLoadedCallback.onFinishInitialLoad()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    private fun resultCallback(callback: LoadRangeCallback<String>) = object : ResultCallback {
        override fun onResult(list: List<String>) {
            callback.onResult(list)
            gifsLoadedCallback.onFinishPageLoad()
        }
    }

    interface GifsLoadedCallback {
        fun onStartInitialLoad() {}
        fun onFinishInitialLoad() {}
        fun onStartPageLoad() {}
        fun onFinishPageLoad() {}
    }
}