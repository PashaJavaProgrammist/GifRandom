package com.haretskiy.pavel.gifrandom.activities

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.ZERO
import com.haretskiy.pavel.gifrandom.adapters.GifAdapter
import com.haretskiy.pavel.gifrandom.databinding.ActivityMainBinding
import com.haretskiy.pavel.gifrandom.utils.Toaster
import com.haretskiy.pavel.gifrandom.viewModels.MainViewModel
import kotlinx.android.synthetic.main.main_content.*
import kotlinx.android.synthetic.main.toolbar.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    
    private val mainViewModel: MainViewModel by viewModel()
    
    private val adapter: GifAdapter by inject()
    
    private val toaster: Toaster by inject()
    
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding.model = mainViewModel
        mainViewModel.setBinding(binding)
        
        initRecyclerView()
        initToolbar()
    }
    
    private fun initRecyclerView() {
        mainViewModel.pagedListLiveData.observe(this, Observer<PagedList<String>> { urls ->
            adapter.submitList(urls)
        })
        mainViewModel.positLiveData.observe(this, Observer {
            if (it == true) {
                rv_gifs.scrollToPosition(ZERO)
            }
        })
        mainViewModel.observeOnline()
                .observe(this, Observer {
                    toaster.showToast(if (it == true) getString(R.string.online_state) else getString(
                            R.string.offline_state))
                })
        rv_gifs.adapter = adapter
    }
    
    private fun initToolbar() {
        setSupportActionBar(toolbar)
    }
    
}