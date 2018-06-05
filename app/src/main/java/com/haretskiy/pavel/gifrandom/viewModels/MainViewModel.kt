package com.haretskiy.pavel.gifrandom.viewModels

import android.arch.lifecycle.ViewModel
import android.view.View
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import com.haretskiy.pavel.gifrandom.utils.Toaster
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val restApi: RestApiImpl,
                    private val toaster: Toaster) : ViewModel() {

    private var d: Disposable? = null

    private fun doAction(limit: Int, rating: String) {
        if (limit <= 25) {
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
            toaster.showToast("Add limit. Limit must be less then 25.", false)
        }
    }

    fun onClickSearch(v: View) {
        doAction(1, "Y")
    }

    override fun onCleared() {
        super.onCleared()
        d?.dispose()
    }
}

//limit.text.toString().toInt(), rating.selectedItem.toString()