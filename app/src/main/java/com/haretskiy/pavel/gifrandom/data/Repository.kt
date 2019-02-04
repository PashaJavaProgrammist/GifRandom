package com.haretskiy.pavel.gifrandom.data

interface Repository {

    fun loadTrendingGifs(rating: String, offset: String) : List<String>
    fun loadGifsByWord(word: String, rating: String, offset: String) : List<String>
}