package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import com.haretskiy.pavel.gifrandom.EMPTY_STRING
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.utils.Toaster
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class MainViewModel(private val context: Application,
                    private val repository: Repository,
                    private val toaster: Toaster) : AndroidViewModel(context) {

    private var compositeDisposable = CompositeDisposable()

    private var gifsList: List<String> = emptyList()

    val updateLiveData = MutableLiveData<Boolean>()
    val gifsLiveData = MutableLiveData<List<String>>()

    val searchWord: ObservableField<String> = ObservableField()
    val ratingSelectedPos = ObservableInt(0)
    val progress = ObservableInt(View.GONE)

    private fun loadGifs(rating: String) {
        progress.set(View.VISIBLE)
        val gifsDis = repository.loadGifs(rating)
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
        compositeDisposable.add(gifsDis)
    }

    private fun loadGifsByWord(word: String, rating: String) {
        progress.set(View.VISIBLE)
        val gifsDis = repository.loadGifsByWord(word, rating)
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
        compositeDisposable.add(gifsDis)
    }

    fun onClickSearch(@Suppress("UNUSED_PARAMETER") v: View) {
        if (searchWord.get().isNullOrEmpty()) {
            loadGifs(getCurrentRating())
        } else {
            loadGifsByWord(searchWord.get() ?: EMPTY_STRING, getCurrentRating())
        }
    }

    private fun getCurrentRating(): String {
        val ratings = context.resources.getStringArray(R.array.ratings)
        return ratings[ratingSelectedPos.get()]
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    fun loadTrendingGifs() {
        if (gifsList.isEmpty()) {
            loadGifs(getCurrentRating())
        } else {
            gifsLiveData.postValue(gifsList)
        }
    }
}

