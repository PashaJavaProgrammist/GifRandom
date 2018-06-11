package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import android.widget.AdapterView
import com.haretskiy.pavel.gifrandom.LIMIT
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsSourceFactory
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsTrendingDataSource
import java.util.concurrent.Executors


class MainViewModel(private val context: Application,
                    private val factory: GifsSourceFactory) : AndroidViewModel(context) {

    private lateinit var config: PagedList.Config
    lateinit var pagedListLiveData: LiveData<PagedList<String>>
    val invalidateLiveData = MutableLiveData<Boolean>()

    val searchWord: ObservableField<String> = ObservableField()
    val ratingSelectedPos = ObservableInt(0)
    val progress = ObservableInt(View.VISIBLE)

    private fun getCurrentRating(): String {
        val ratings = context.resources.getStringArray(R.array.ratings)
        return ratings[ratingSelectedPos.get()]
    }

    fun initPaging() {
        config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(LIMIT)
                .build()

        pagedListLiveData = LivePagedListBuilder(
                factory.initCallback(object : GifsTrendingDataSource.GifsLoadedCallback {
                    override fun onStartInitialLoad() {
                        progress.set(View.VISIBLE)
                    }

                    override fun onFinishInitialLoad() {
                        progress.set(View.GONE)
                    }
                }), config).setFetchExecutor(Executors.newSingleThreadExecutor()).build()
    }

    fun onClickSearch(@Suppress("UNUSED_PARAMETER") v: View) {
        //        todo: need to search by word
    }

    @Suppress("UNUSED_PARAMETER")
    fun onRatingSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        //        todo: need to search by new rating
        invalidateLiveData.postValue(true)
    }

}

