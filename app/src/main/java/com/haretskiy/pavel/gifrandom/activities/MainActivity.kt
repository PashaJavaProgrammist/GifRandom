package com.haretskiy.pavel.gifrandom.activities

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.adapters.GifAdapter
import com.haretskiy.pavel.gifrandom.databinding.ActivityMainBinding
import com.haretskiy.pavel.gifrandom.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val adapter: GifAdapter by inject()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.initPaging()
        binding.model = mainViewModel

        initRecyclerView()
        initObservers()
    }

    private fun initRecyclerView() {

        mainViewModel.pagedListLiveData.observe(this, Observer<PagedList<String>> { urls ->
            adapter.submitList(urls)
        })
        rv_gifs.adapter = adapter
    }

    private fun initObservers() {
        mainViewModel.invalidateLiveData.observe(this, Observer {
            if (it == true) {
                rv_gifs.invalidate()
            }
        })
    }

}