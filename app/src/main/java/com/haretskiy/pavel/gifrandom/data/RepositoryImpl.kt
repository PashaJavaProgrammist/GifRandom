package com.haretskiy.pavel.gifrandom.data

import android.util.Log
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.GifResponse
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import kotlinx.coroutines.*
import java.net.ConnectException

class RepositoryImpl(private val restApi: RestApiImpl) : Repository {
    
    override fun loadTrendingGifs(rating: String, offset: String) = load(restApi.loadGifsAsync(
            rating,
            offset))
    
    override fun loadGifsByWord(word: String,
                                rating: String,
                                offset: String) = load(restApi.loadGifsByWordAsync(word,
            rating,
            offset))
    
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.d("RepositoryImpl", "Caught $exception")
        exception.printStackTrace()
    }
    
    private fun load(deferred: Deferred<GifResponse>): List<String> = runBlocking(Dispatchers.Default + handler) {
        try {
            val responseData = deferred.await()
            Log.d("RepositoryImpl", "On success")
            convertDataAsync(responseData).await()
        } catch (ex: ConnectException) {
            Log.d("RepositoryImpl", "On error$ex")
            emptyList<String>()
        }
    }
    
    private fun convertDataAsync(responseData: GifResponse) = GlobalScope.async(Dispatchers.Default + handler) {
        responseData.data.map {
            it.images?.original?.url ?: EMPTY_STRING
        }
    }
    
}