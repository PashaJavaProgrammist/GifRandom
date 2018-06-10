package com.haretskiy.pavel.gifrandom.data

import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.GifResponse
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import io.reactivex.Observable

class RepositoryImpl(
        private val restApi: RestApiImpl) : Repository {

    override fun loadGifs(rating: String, offset: String): Observable<List<String>> =
            transformToListString(restApi.loadGifs(rating))

    override fun loadGifsByWord(word: String, rating: String, offset: String): Observable<List<String>> =
            transformToListString(restApi.loadGifsByWord(word, rating))

    private fun transformToListString(response: Observable<GifResponse>): Observable<List<String>> =
            response.map {
                val list = it.data.map {
                    it.images?.original?.url ?: EMPTY_STRING
                }
                list
            }

}