package com.haretskiy.pavel.gifrandom.activities

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.adapters.GifAdapter
import com.haretskiy.pavel.gifrandom.databinding.ActivityMainBinding
import com.haretskiy.pavel.gifrandom.utils.ImageLoader
import com.haretskiy.pavel.gifrandom.utils.Router
import com.haretskiy.pavel.gifrandom.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val imageLoader: ImageLoader by inject()
    private val router: Router by inject()

    private val adapter = GifAdapter(emptyList(), imageLoader, router)

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.model = mainViewModel

        initRecyclerView()

        initObservers()
    }

    private fun initObservers() {
        mainViewModel.updateLiveData.observe(this, Observer {
            if (it == true) binding.invalidateAll()
        })

        mainViewModel.gifsLiveData.observe(this, Observer {
            if (it != null) {
                adapter.gifsList = it
                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun initRecyclerView() {
        rv_gifs.adapter = adapter
    }

}
