package com.haretskiy.pavel.gifrandom.activities

import android.arch.lifecycle.Observer
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.gifrandom.LIMIT
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.adapters.GifAdapter
import com.haretskiy.pavel.gifrandom.data.Repository
import com.haretskiy.pavel.gifrandom.databinding.ActivityMainBinding
import com.haretskiy.pavel.gifrandom.utils.*
import com.haretskiy.pavel.gifrandom.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject
import java.util.concurrent.Executors


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val imageLoader: ImageLoader by inject()
    private val router: Router by inject()
    private val diffCallBack: DiffCallBack by inject()
    private val gifsDataSource: GifsDataSource by inject()
    private val mainThreadExecutor: MainThreadExecutor by inject()
    private val repository: Repository by inject()

    private var adapter = GifAdapter(diffCallBack, emptyList(), imageLoader, router)

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.model = mainViewModel

        initRecyclerView()

//        initObservers()

//        loadTrendingGifs()
    }

    private fun loadTrendingGifs() {
        mainViewModel.loadTrendingGifs()
    }

//    private fun initObservers() {
//        mainViewModel.updateLiveData.observe(this, Observer {
//            if (it == true) binding.invalidateAll()
//        })
//
//        mainViewModel.gifsLiveData.observe(this, Observer {
//            if (it != null) {
//                adapter.gifsList = it
//                adapter.notifyDataSetChanged()
//            }
//        })
//    }

    private fun initRecyclerView() {

        val factory = GifsSourceFactory(repository)

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(LIMIT)
                .build()

        val pagedListLiveData = LivePagedListBuilder(factory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()

        pagedListLiveData.observe(this, object : Observer<PagedList<String>> {
            override fun onChanged(urls: PagedList<String>?) {
                adapter.submitList(urls)
            }
        })

//        val pagedList = PagedList.Builder(gifsDataSource, config)
//                .setNotifyExecutor { mainThreadExecutor }
//                .setFetchExecutor { Executors.newSingleThreadExecutor() }
////                .setMainThreadExecutor(mainThreadExecutor)
////                .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
//                .build()

//        adapter.submitList(pagedList)
        rv_gifs.adapter = adapter
    }

}
