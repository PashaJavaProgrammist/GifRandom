package com.haretskiy.pavel.gifrandom.data

import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import io.reactivex.Observable

class RepositoryImpl(
        private val storage: Storage,
        private val restApi: RestApiImpl
) : Repository {

    override fun loadGifs(limit: Int, rating: String): Observable<List<String>> =
            restApi.loadGifs(limit, rating).map {
                val list = it.data.map { it.images?.original?.url ?: EMPTY_STRING }
                storage.addItems(list)
                storage.getAll()
            }
}