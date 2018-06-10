package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.utils.Toaster
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val context: Application,
                    private val repository: Repository,
                    private val toaster: Toaster) : AndroidViewModel(context) {

    private var d: Disposable? = null

    private var gifsList: List<String> = emptyList()

    val updateLiveData = MutableLiveData<Boolean>()
    val gifsLiveData = MutableLiveData<List<String>>()

    val limit: ObservableField<String> = ObservableField("1")
    val ratingSelectedPos = ObservableInt(0)
    val progress = ObservableInt(View.GONE)

    private fun doAction(rating: String) {
        progress.set(View.VISIBLE)
        d = repository.loadGifs(rating)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (it != null) {
                                gifsList = it
                                gifsLiveData.postValue(gifsList)
                            }
                            progress.set(View.GONE)
                        },
                        {
                            toaster.showToast("Error: $it", false)
                            progress.set(View.GONE)
                        }
                )
    }

    fun onClickSearch(@Suppress("UNUSED_PARAMETER") v: View) {
        doAction(getCurrentRating())
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

