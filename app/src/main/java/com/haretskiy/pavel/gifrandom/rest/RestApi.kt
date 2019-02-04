package com.haretskiy.pavel.gifrandom.rest

import com.haretskiy.pavel.gifrandom.ZERO_OFFSET
import com.haretskiy.pavel.gifrandom.models.GifResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    
    @GET("gifs/trending")
    fun loadGifsAsync(@Query("limit") limit: Int, @Query("rating") rating: String, @Query("offset") offset: String = ZERO_OFFSET): Deferred<GifResponse>
    
    @GET("gifs/search")
    fun loadGifsBySearchWordAsync(@Query("q") searchWord: String, @Query("limit") limit: Int, @Query("rating") rating: String, @Query(
            "offset") offset: String = ZERO_OFFSET): Deferred<GifResponse>
}