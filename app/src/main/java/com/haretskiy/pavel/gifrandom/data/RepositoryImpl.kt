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
        transformToListString(restApi.loadGifs(rating, offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null) {
                                resultCallback.onResult(it)
                            }
                        })
    }

    override fun loadGifsByWord(word: String, rating: String, offset: String, resultCallback: ResultCallback) {
        transformToListString(restApi.loadGifsByWord(word, rating, offset))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null) {
                                resultCallback.onResult(it)
                            }
                        })
    }

    private fun transformToListString(response: Observable<GifResponse>): Observable<List<String>> =
            response.map {
                val list = it.data.map {
                    it.images?.original?.url ?: EMPTY_STRING
                }
                list
            }

    interface ResultCallback {
        fun onResult(list: List<String>)
    }

}