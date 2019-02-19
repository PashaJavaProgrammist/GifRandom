package com.haretskiy.pavel.gifrandom.rest

import com.haretskiy.pavel.gifrandom.API_KEY
import com.haretskiy.pavel.gifrandom.PAGE_SIZE
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET

class RestApiImpl(private val restApi: RestApi) {

    fun loadGifsAsync(rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifsAsync(API_KEY, PAGE_SIZE, rating, offset)

    fun loadGifsByWordAsync(word: String, rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifsBySearchWordAsync(API_KEY, word, PAGE_SIZE, rating, offset)

}