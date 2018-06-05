package com.haretskiy.pavel.gifrandom.rest

import com.haretskiy.pavel.gifrandom.API_KEY

class RestApiImpl(private val restApi: RestApi) {

    fun loadGifs(limit: Int, rating: String) = restApi.loadGifs(API_KEY, limit, rating)

}