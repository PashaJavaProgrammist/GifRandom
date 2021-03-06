package com.haretskiy.pavel.gifrandom.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v4.view.ViewCompat
import android.support.v7.app.AppCompatActivity
import com.haretskiy.pavel.gifrandom.*
import com.haretskiy.pavel.gifrandom.databinding.ActivityDetailBinding
import com.haretskiy.pavel.gifrandom.viewModels.DetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.ParameterList

class DetailActivity : AppCompatActivity() {

    private var url = EMPTY_STRING

    private val detailViewModel: DetailViewModel by viewModel { ParameterList(url) }

    private val binding: ActivityDetailBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_detail) as ActivityDetailBinding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = intent.getStringExtra(BUNDLE_KEY_URL_DETAIL)

        binding.detailModel = detailViewModel
        detailViewModel.initObservers(this)

        makeTransition()
    }

    private fun makeTransition() {
        ViewCompat.setTransitionName(binding.imageView, VIEW_NAME_IMAGE)
        Handler().postDelayed({ recreate() }, START_ANIMATION_DELAY)
    }
}
