package com.haretskiy.pavel.gifrandom.rest

import com.haretskiy.pavel.gifrandom.LIMIT
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET

class RestApiImpl(private val restApi: RestApi) {

    fun loadGifsAsync(rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifsAsync(LIMIT, rating, offset)

    fun loadGifsByWordAsync(word: String, rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifsBySearchWordAsync(word, LIMIT, rating, offset)

}