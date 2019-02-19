package com.haretskiy.pavel.gifrandom.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch

class ConnectivityListener(private val context: Context,
                           private val actor: SendChannel<Intent>,
                           private val intentFilter: IntentFilter,
                           private val broadcastPermission: String?,
                           private val schedulerHandler: Handler?) {
    
    private lateinit var broadcastReceiver: BroadcastReceiver
    
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Error", "Caught $exception")
        exception.printStackTrace()
    }
    
    fun start() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                GlobalScope.launch(handler) {
                    actor.send(intent)
                }
            }
        }
        
        context.registerReceiver(broadcastReceiver,
                intentFilter,
                broadcastPermission,
                schedulerHandler)
    }
    
    fun stop() {
        GlobalScope.launch(handler) {
            context.unregisterReceiver(broadcastReceiver)
        }
    }
}