package com.haretskiy.pavel.gifrandom.retrofit

import com.haretskiy.pavel.gifrandom.models.GifResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface IRestService {

    @GET("gifs/trending")
    fun loadGifs(@Query("api_key") aipKey: String,
                 @Query("limit") limit: Int,
                 @Query("rating") rating: String): Observable<GifResponse>
}