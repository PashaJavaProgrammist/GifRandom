package com.haretskiy.pavel.gifrandom.data

import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import io.reactivex.Observable

class RepositoryImpl(
        private val restApi: RestApiImpl) : Repository {

    override fun loadGifs(rating: String, offset: String): Observable<List<String>> =
            restApi.loadGifs(rating).map {
                val list = it.data.map {
                    it.images?.original?.url ?: EMPTY_STRING
                }
                list
            }
}