package com.haretskiy.pavel.gifrandom.utils.pagging

import android.arch.paging.DataSource
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.data.Repository

class GifsSourceFactory(private val repository: Repository) : DataSource.Factory<Int, String>() {

    var callback = object : GifsDataSource.GifsLoadedCallback {}
    private lateinit var dataSource: GifsDataSource
    var rating = "Y"
    var word: String = EMPTY_STRING

    fun initCallback(callback: GifsDataSource.GifsLoadedCallback): GifsSourceFactory {
        this.callback = callback
        return this
    }

    override fun create(): DataSource<Int, String> {
        dataSource = GifsDataSource(repository, callback, rating, word)
        return dataSource
    }

    fun invalidate() {
        dataSource.invalidate()
    }
}