package com.haretskiy.pavel.gifrandom.retrofit

import com.haretskiy.pavel.gifrandom.APIKEY
import com.haretskiy.pavel.gifrandom.models.GifResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url


interface IRestService {

    @GET("gifs/trending/${APIKEY}")
    fun loadGifs(@Query("limit") limit: Int,
                 @Query("rating") rating: String): Observable<GifResponse>
}