package com.haretskiy.pavel.gifrandom.utils

import android.arch.lifecycle.MutableLiveData

class ProgressController {

    var liveData = MutableLiveData<Boolean>()

    fun setProgressVisibility(value: Boolean) {
        liveData.postValue(value)
    }
}