package com.haretskiy.pavel.gifrandom.data

import io.reactivex.Observable

interface Repository {
    fun loadGifs(limit: Int, rating: String): Observable<List<String>>
}