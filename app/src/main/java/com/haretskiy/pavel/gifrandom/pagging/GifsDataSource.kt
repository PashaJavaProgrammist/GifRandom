package com.haretskiy.pavel.gifrandom.pagging

import android.arch.paging.PositionalDataSource
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET
import com.haretskiy.pavel.gifrandom.data.Repository

class GifsDataSource(private val repository: Repository,
                     private var gifsLoadedCallback: GifsLoadedCallback,
                     private var rating: String,
                     private var word: String) : PositionalDataSource<String>() {
    
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<String>) {
        gifsLoadedCallback.onStartPageLoad()
        when {
            word.isEmpty() -> handleLoadRange(repository.loadTrendingGifs(rating,
                    params.startPosition.toString()), callback)
            else -> handleLoadRange(repository.loadGifsByWord(word,
                    rating,
                    params.startPosition.toString()), callback)
        }
    }
    
    private fun handleLoadRange(listOfGifs: List<String>,
                                callback: LoadRangeCallback<String>) {
        callback.onResult(listOfGifs)
        gifsLoadedCallback.onFinishPageLoad()
    }
    
    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<String>) {
        gifsLoadedCallback.onStartInitialLoad()
        when {
            word.isEmpty() -> handleLoadInitial(repository.loadTrendingGifs(rating, ZERO_OFFSET),
                    params,
                    callback)
            else -> handleLoadInitial(repository.loadGifsByWord(word, rating, ZERO_OFFSET),
                    params,
                    callback)
        }
    }
    
    private fun handleLoadInitial(listOfGifs: List<String>,
                                  params: LoadInitialParams,
                                  callback: LoadInitialCallback<String>) {
        var startPos = params.requestedStartPosition
        when {
            startPos < 0 -> startPos = 0
        }
        callback.onResult(listOfGifs, startPos)
        gifsLoadedCallback.onFinishInitialLoad()
        
    }
    
    interface GifsLoadedCallback {
        fun onStartInitialLoad() {}
        fun onFinishInitialLoad() {}
        fun onStartPageLoad() {}
        fun onFinishPageLoad() {}
    }
}