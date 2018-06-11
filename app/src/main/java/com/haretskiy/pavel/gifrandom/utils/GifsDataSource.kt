package com.haretskiy.pavel.gifrandom.utils

import android.arch.paging.PositionalDataSource
import com.haretskiy.pavel.gifrandom.ZERO_OFFSET
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.data.RepositoryImpl

class GifsDataSource(private val repository: Repository) : PositionalDataSource<String>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<String>) {
        repository.loadGifs("Y", params.startPosition.toString(), object : RepositoryImpl.ResultCallback {
            override fun onResult(list: List<String>) {
                callback.onResult(list)
            }

        })
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<String>) {
        repository.loadGifs("Y", ZERO_OFFSET, object : RepositoryImpl.ResultCallback {
            override fun onResult(list: List<String>) {
                callback.onResult(list, 0)
            }
        })
    }
}