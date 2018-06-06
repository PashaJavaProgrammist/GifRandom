package com.haretskiy.pavel.gifrandom.activities

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.adapters.GifAdapter
import com.haretskiy.pavel.gifrandom.databinding.ActivityMainBinding
import com.haretskiy.pavel.gifrandom.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.architecture.ext.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private val adapter = GifAdapter(emptyList())

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
