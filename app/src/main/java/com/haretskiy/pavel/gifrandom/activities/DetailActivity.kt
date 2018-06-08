package com.haretskiy.pavel.gifrandom.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.databinding.ActivityDetailBinding
import com.haretskiy.pavel.gifrandom.viewModels.DetailViewModel
import org.koin.android.architecture.ext.viewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailViewModel by viewModel()

    private val binding: ActivityDetailBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_detail) as ActivityDetailBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.detailModel = detailViewModel
        detailViewModel.initObservers(this)
    }
}
