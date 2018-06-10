package com.haretskiy.pavel.gifrandom.data

import com.haretskiy.pavel.gifrandom.ZERO_OFFSET
import io.reactivex.Observable

interface Repository {
    fun loadGifs(rating: String, offset: String = ZERO_OFFSET): Observable<List<String>>
}