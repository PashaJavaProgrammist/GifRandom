package com.haretskiy.pavel.gifrandom.utils

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.util.Log
import com.haretskiy.pavel.gifrandom.utils.Connectivity.Companion.TAG

class ConnectivityLiveData(private val context: Context,
                           private val intentFilter: IntentFilter,
                           private val broadcastPermission: String?,
                           private val schedulerHandler: Handler?) : LiveData<Intent>() {
    
    private lateinit var broadcastReceiver: BroadcastReceiver
    
    override fun observe(owner: LifecycleOwner, observer: Observer<Intent>) {
        super.observe(owner, observer)
        
        observe()
    }
    
    override fun observeForever(observer: Observer<Intent>) {
        super.observeForever(observer)
        
        observe()
    }
    
    private fun observe() {
        try {
            broadcastReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    postValue(intent)
                }
            }
            
            context.registerReceiver(broadcastReceiver,
                    intentFilter,
                    broadcastPermission,
                    schedulerHandler)
        } catch (t: Throwable) {
            Log.e(TAG, t.toString())
        }
    }
    
    private fun removeObserver() {
        try {
            context.unregisterReceiver(broadcastReceiver)
        } catch (t: Throwable) {
            Log.e(TAG, t.toString())
        }
    }
    
    override fun removeObserver(observer: Observer<Intent>) {
        super.removeObserver(observer)
        removeObserver()
    }
    
    override fun removeObservers(owner: LifecycleOwner) {
        super.removeObservers(owner)
        removeObserver()
    }
}