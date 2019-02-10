package com.haretskiy.pavel.gifrandom.rest

import com.haretskiy.pavel.gifrandom.PAGE_SIZE
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET

class RestApiImpl(private val restApi: RestApi) {

    fun loadGifsAsync(rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifsAsync(PAGE_SIZE, rating, offset)

    fun loadGifsByWordAsync(word: String, rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifsBySearchWordAsync(word, PAGE_SIZE, rating, offset)

}