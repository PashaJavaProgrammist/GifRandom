package com.haretskiy.pavel.gifrandom.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import android.widget.AdapterView
import com.haretskiy.pavel.gifrandom.*
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsDataSource
import com.haretskiy.pavel.gifrandom.utils.pagging.GifsSourceFactory
import java.util.concurrent.Executors


class MainViewModel(private val context: Application,
                    private val factory: GifsSourceFactory) : AndroidViewModel(context) {

    private lateinit var config: PagedList.Config
    lateinit var pagedListLiveData: LiveData<PagedList<String>>

    val searchWord: ObservableField<String> = ObservableField()
    val ratingSelectedPos = ObservableInt(ZERO)
    val progress = ObservableInt(View.VISIBLE)

    fun initPaging() {
        config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(INITIAL_LOAD_SIZE)
                .setPrefetchDistance(PREFETCH_SIZE)
                .setPageSize(LIMIT)
                .build()

        pagedListLiveData = LivePagedListBuilder(
                factory.initCallback(object : GifsDataSource.GifsLoadedCallback {
                    override fun onStartInitialLoad() {
                        progress.set(View.VISIBLE)
                    }

                    override fun onFinishInitialLoad() {
                        progress.set(View.GONE)
                    }
                }), config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()
    }

    private fun getCurrentRating(): String {
        val ratings = context.resources.getStringArray(R.array.ratings)
        return ratings[ratingSelectedPos.get()]
    }

    fun onClickSearch(@Suppress("UNUSED_PARAMETER") v: View) {
        factory.rating = getCurrentRating()
        factory.word = searchWord.get() ?: EMPTY_STRING
        factory.invalidate()
        //todo: problem with position
    }

    @Suppress("UNUSED_PARAMETER")
    fun onRatingSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }

}

