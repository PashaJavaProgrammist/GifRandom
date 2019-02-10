package com.haretskiy.pavel.gifrandom.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.haretskiy.pavel.gifrandom.distinctUntilChanged
import com.haretskiy.pavel.gifrandom.map

class Connectivity(context: Context, private val manager: ConnectivityManager) {
    
    private val connectivityLiveData = ConnectivityLiveData(context,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION),
            null,
            null)
    
    companion object {
        const val TAG = "Connectivity"
    }
    
    @Suppress("DEPRECATION")
    @SuppressLint("MissingPermission")
    fun isOnline(): Boolean {
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
    
    fun onlineChanges() = connectivityLiveData.map { isOnline() }.distinctUntilChanged()
    
}