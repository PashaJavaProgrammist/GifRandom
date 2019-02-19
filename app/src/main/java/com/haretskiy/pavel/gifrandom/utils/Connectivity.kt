package com.haretskiy.pavel.gifrandom.utils

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.haretskiy.pavel.gifrandom.distinctUntilChanged
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.coroutines.CoroutineContext

class Connectivity(context: Context, private val manager: ConnectivityManager) : CoroutineScope {
    
    private var isStarted = false
    
    private val job: Job = Job()
    
    override val coroutineContext: CoroutineContext
        get() = job
    
    private val onlineData = MutableLiveData<Boolean>()
    
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Error", "Caught $exception")
        exception.printStackTrace()
    }
    
    private val connectActor = actor<Intent>(Dispatchers.Default + handler) {
        for (intents in channel) {
            onlineData.postValue(isOnlineAsync())
        }
    }
    
    fun start() {
        if (!isStarted) {
            connectivityListener.start()
            isStarted = true
        }
    }
    
    fun stop() {
        if (isStarted) {
            connectivityListener.stop()
            isStarted = false
            job.cancel()
        }
    }
    
    private val connectivityListener = ConnectivityListener(context,
            connectActor,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION),
            null,
            null)
    
    private fun isOnline(): Boolean {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            val activeNetworks = manager.allNetworks
            for (network in activeNetworks) {
                val networkInfo = manager.getNetworkInfo(network)
                if (networkInfo != null && networkInfo.isConnected) {
                    Log.d(TAG, "isOnline true: $networkInfo")
                    return true
                }
            }
        } else {
            val networksInfo = this.manager.allNetworkInfo
            if (networksInfo != null) for (networkInfo in networksInfo) {
                if (networkInfo != null && networkInfo.state == NetworkInfo.State.CONNECTED) {
                    Log.d(TAG, "isOnline true: $networkInfo.toString()")
                    return true
                }
            }
        }
        
        Log.d(TAG, "isOnline false")
        
        return false
    }
    
    private fun isOnlineAsync(): Boolean {
        return runBlocking {
            withContext(Dispatchers.Default + handler) { isOnline() }
        }
    }
    
    fun onlineChanges(): LiveData<Boolean> {
        return onlineData.distinctUntilChanged()
    }
    
    companion object {
        const val TAG = "Connectivity"
    }
    
}