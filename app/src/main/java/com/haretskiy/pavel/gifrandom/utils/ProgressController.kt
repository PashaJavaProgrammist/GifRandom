package com.haretskiy.pavel.gifrandom.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

class ProgressController {

    private var liveData = MutableLiveData<Boolean>()

    fun setProgressVisibility(value: Boolean) {
        liveData.postValue(value)
    }

    fun subscribeOnProgressChanges(lifecycleOwner: LifecycleOwner, observer: Observer<Boolean>) {
        liveData.observe(lifecycleOwner, observer)
    }


}