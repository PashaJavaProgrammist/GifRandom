package com.haretskiy.pavel.gifrandom.data

interface Repository {
//    fun loadTrendingGifs(rating: String, offset: String = ZERO_OFFSET): Observable<List<String>>
//    fun loadGifsByWord(word: String, rating: String, offset: String = ZERO_OFFSET): Observable<List<String>>

    fun loadTrendingGifs(rating: String, offset: String, resultCallback: RepositoryImpl.ResultCallback)
    fun loadGifsByWord(word: String, rating: String, offset: String, resultCallback: RepositoryImpl.ResultCallback)
}