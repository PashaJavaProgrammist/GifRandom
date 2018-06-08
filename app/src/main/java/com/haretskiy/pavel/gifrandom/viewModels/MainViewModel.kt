package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.models.Data
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import com.haretskiy.pavel.gifrandom.utils.Toaster
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val context: Application,
                    private val restApi: RestApiImpl,
                    private val toaster: Toaster) : AndroidViewModel(context) {

    private var d: Disposable? = null

    private var gifsList: List<Data> = emptyList()

    val updateLiveData = MutableLiveData<Boolean>()
    val gifsLiveData = MutableLiveData<List<Data>>()

    val limit: ObservableField<String> = ObservableField("1")
    val ratingSelectedPos = ObservableInt(0)
    val progress = ObservableInt(View.GONE)

    private fun doAction(limit: Int, rating: String) {
        progress.set(View.VISIBLE)
        if (limit in 1..25) {
            d = restApi.loadGifs(limit, rating)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                if (it != null) {
                                    gifsList = it.data
                                    gifsLiveData.postValue(gifsList)
                                }
                                progress.set(View.GONE)
                            },
                            {
                                toaster.showToast("Error: $it", false)
                                progress.set(View.GONE)
                            }
                    )
        } else {
            toaster.showToast("Add limit. Limit must be less then 25 and more then 0.", false)
        }
    }

    fun onClickSearch(@Suppress("UNUSED_PARAMETER") v: View) {
        if (!limit.get().isNullOrEmpty()) {
            val l = limit.get()?.toInt() ?: 0
            doAction(l, getCurrentRating())
        } else {
            toaster.showToast("Add limit. Limit must be less then 25 and more then 0.", false)
        }
    }

    private fun getCurrentRating(): String {
        val ratings = context.resources.getStringArray(R.array.ratings)
        return ratings[ratingSelectedPos.get()]
    }

    override fun onCleared() {
        super.onCleared()
        d?.dispose()
    }
}

