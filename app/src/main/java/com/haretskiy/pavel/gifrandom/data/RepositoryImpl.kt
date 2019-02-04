package com.haretskiy.pavel.gifrandom.data

import android.util.Log
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.models.GifResponse
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import kotlinx.coroutines.*

class RepositoryImpl(private val restApi: RestApiImpl) : Repository {
    
    override fun loadTrendingGifs(rating: String, offset: String) = load(restApi.loadGifsAsync(
            rating,
            offset))
    
    override fun loadGifsByWord(word: String,
                                rating: String,
                                offset: String) = load(restApi.loadGifsByWordAsync(word,
            rating,
            offset))
    
    private fun load(deferred: Deferred<GifResponse>): List<String> = runBlocking {
        GlobalScope.async(CoroutineExceptionHandler { _, exception ->
            Log.d("RepositoryImpl", "Caught $exception")
        }, CoroutineStart.DEFAULT, null, {
            val responseData = deferred.await()
            Log.d("RepositoryImpl", "On success")
            convertData(responseData)
        })
                .await()
    }
    
    private suspend fun convertData(responseData: GifResponse) = GlobalScope.async(Dispatchers.Default,
            CoroutineStart.DEFAULT,
            null,
            { responseData.data.map { it.images?.original?.url ?: EMPTY_STRING } }).await()
    
}