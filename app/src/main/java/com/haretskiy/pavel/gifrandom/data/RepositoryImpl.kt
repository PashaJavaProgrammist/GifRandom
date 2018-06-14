package com.haretskiy.pavel.gifrandom.data

import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.GifResponse
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepositoryImpl(
        private val restApi: RestApiImpl) : Repository {

    override fun loadTrendingGifs(rating: String, offset: String, resultCallback: ResultCallback) {
        load(restApi.loadGifs(rating, offset), resultCallback)
    }

    override fun loadGifsByWord(word: String, rating: String, offset: String, resultCallback: ResultCallback) {
        load(restApi.loadGifsByWord(word, rating, offset), resultCallback)
    }

    private fun load(obs: Observable<GifResponse>, resultCallback: ResultCallback) {
        obs.subscribeOn(Schedulers.io())
                .map {
                    it.data.map {
                        it.images?.original?.url ?: EMPTY_STRING
                    }
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null) {
                                resultCallback.onResult(it)
                            }
                        },
                        {
                            resultCallback.onResult(emptyList())
                        })
    }

    interface ResultCallback {
        fun onResult(list: List<String>)
    }

}