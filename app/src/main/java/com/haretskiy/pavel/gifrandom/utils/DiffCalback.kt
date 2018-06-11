package com.haretskiy.pavel.gifrandom.utils

import android.support.v7.util.DiffUtil

class DiffCallBack : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}