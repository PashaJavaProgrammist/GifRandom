package com.haretskiy.pavel.gifrandom.utils.pagging

import android.arch.paging.DataSource
import com.haretskiy.pavel.gifrandom.data.Repository

class GifsSourceFactory(private val repository: Repository) : DataSource.Factory<Int, String>() {

    var callback = object : GifsTrendingDataSource.GifsLoadedCallback {}
    private lateinit var dataSource: GifsTrendingDataSource

    fun initCallback(callback: GifsTrendingDataSource.GifsLoadedCallback): GifsSourceFactory {
        this.callback = callback
        return this
    }

    override fun create(): DataSource<Int, String> {
        dataSource = GifsTrendingDataSource(repository, callback)
        return dataSource
    }

    fun invalidate() {
        dataSource.invalidate()
    }
}