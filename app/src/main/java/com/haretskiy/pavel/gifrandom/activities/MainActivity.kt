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
import com.haretskiy.pavel.gifrandom.utils.DiffCallBack
import com.haretskiy.pavel.gifrandom.utils.GifsSourceFactory
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.Router
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
    private val repository: Repository by inject()

    private var adapter = GifAdapter(diffCallBack, imageLoader, router)

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.model = mainViewModel

        initRecyclerView()
    }

    private fun initRecyclerView() {

        val factory = GifsSourceFactory(repository)

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(LIMIT)
                .build()

        val pagedListLiveData = LivePagedListBuilder(factory, config)
                .setFetchExecutor(Executors.newSingleThreadExecutor())
                .build()

        pagedListLiveData.observe(this, Observer<PagedList<String>> { urls -> adapter.submitList(urls) })

        rv_gifs.adapter = adapter
    }

}
