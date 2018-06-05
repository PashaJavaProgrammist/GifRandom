package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import com.haretskiy.pavel.gifrandom.utils.Toaster
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val context: Application,
                    private val restApi: RestApiImpl,
                    private val toaster: Toaster) : AndroidViewModel(context) {

    private var d: Disposable? = null

    var limit: ObservableField<String> = ObservableField()
    var ratingSelectedPos: ObservableInt = ObservableInt()

    private fun doAction(limit: Int, rating: String) {
        if (limit in 1..25) {
            d = restApi.loadGifs(limit, rating)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                toaster.showToast("Data: ${it.data}", true)
                            },
                            {
                                toaster.showToast("Error: $it", false)
                            }
                    )
        } else {
            toaster.showToast("Add limit. Limit must be less then 25 and more then 0.", false)
        }
    }

    fun onClickSearch(v: View) {
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

