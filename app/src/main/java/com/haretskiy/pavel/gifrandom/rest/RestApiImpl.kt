package com.haretskiy.pavel.gifrandom.rest

import com.haretskiy.pavel.gifrandom.API_KEY
import com.haretskiy.pavel.gifrandom.LIMIT

class RestApiImpl(private val restApi: RestApi) {

    fun loadGifs(rating: String) = restApi.loadGifs(API_KEY, LIMIT, rating)

}