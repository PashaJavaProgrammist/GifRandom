package com.haretskiy.pavel.gifrandom.utils

import android.arch.paging.DataSource
import com.haretskiy.pavel.gifrandom.data.Repository

class GifsSourceFactory(private val repository: Repository) : DataSource.Factory<Int, String>() {

    override fun create(): DataSource<Int, String> =
            GifsDataSource(repository)
}