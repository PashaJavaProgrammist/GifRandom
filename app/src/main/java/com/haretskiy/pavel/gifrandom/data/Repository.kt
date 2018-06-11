package com.haretskiy.pavel.gifrandom.data

interface Repository {
//    fun loadGifs(rating: String, offset: String = ZERO_OFFSET): Observable<List<String>>
//    fun loadGifsByWord(word: String, rating: String, offset: String = ZERO_OFFSET): Observable<List<String>>

    fun loadGifs(rating: String, offset: String, resultCallback: RepositoryImpl.ResultCallback)
    fun loadGifsByWord(word: String, rating: String, offset: String, resultCallback: RepositoryImpl.ResultCallback)
}