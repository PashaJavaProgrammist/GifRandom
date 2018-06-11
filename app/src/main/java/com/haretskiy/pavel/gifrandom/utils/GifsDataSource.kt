package com.haretskiy.pavel.gifrandom.utils

import android.arch.paging.PositionalDataSource
import com.haretskiy.pavel.gifrandom.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class GifsDataSource(private val repository: Repository) : PositionalDataSource<String>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<String>) {
        repository.loadGifs("Y", params.startPosition.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null) {
                                callback.onResult(it)
                            }
                        })
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<String>) {
        repository.loadGifs("Y")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null) {
                                callback.onResult(it, 0)
                            }
                        })
    }
}