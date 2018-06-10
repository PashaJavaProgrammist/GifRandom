package com.haretskiy.pavel.gifrandom.rest

import com.haretskiy.pavel.gifrandom.API_KEY
import com.haretskiy.pavel.gifrandom.LIMIT
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET

class RestApiImpl(private val restApi: RestApi) {

    fun loadGifs(rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifs(API_KEY, LIMIT, rating, offset)

    fun loadGifsByWord(word: String, rating: String, offset: String = ZERO_OFFSET) = restApi.loadGifsBySearchWord(API_KEY, word, LIMIT, rating, offset)

}