package com.haretskiy.pavel.gifrandom.utils

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlin.coroutines.CoroutineContext

object ActivityCounter : CoroutineScope {
    
    private val job = Job()
    
    private val actionStore = HashMap<Class<Any>, (Count) -> Unit>()
    
    private val handler = CoroutineExceptionHandler { _, exception ->
        Log.e("Error", "Caught $exception")
        exception.printStackTrace()
    }
    
    override val coroutineContext: CoroutineContext
        get() = job + handler
    
    private var counter: Int = 0
    
    private val connectActor = actor<Count>(Dispatchers.Default) {
        var prevCount: Count? = null
        for (count in channel) {
            if (count != prevCount) {
                for ((_, action) in actionStore) {
                    GlobalScope.launch(Dispatchers.Default) {
                        action.invoke(count)
                    }
                }
                prevCount = count
                
                Log.d("ActivityCounter",
                        "Activities: old count =${count.previousValue}, new count =${count.newValue}")
            }
        }
    }
    
    fun addAction(clazz: Class<Any>, action: (count: Count) -> Unit) {
        actionStore[clazz] = action
    }
    
    fun removeAction(clazz: Class<Any>) {
        actionStore.remove(clazz)
    }
    
    fun init(app: Application) {
        app.registerActivityLifecycleCallbacks(object : SimpleActivityLifecycleCallbacks() {
            
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                
                val count = Count()
                count.previousValue = counter
                
                counter++
                Log.d("ActivityCounter", "onActivityCreated ${activity.javaClass.name} ")
                
                count.newValue = counter
                
                GlobalScope.launch {
                    connectActor.send(count)
                }
            }
            
            override fun onActivityDestroyed(activity: Activity) {
                
                val count = Count()
                count.previousValue = counter
                
                counter--
                Log.d("ActivityCounter", "onActivityDestroyed ${activity.javaClass.name} ")
                
                count.newValue = counter
                GlobalScope.launch {
                    connectActor.send(count)
                }
                if (count.newValue == 0) {
                    Log.d("ActivityCounter", "0 activities, Cancel job")
                }
            }
        })
    }
    
    class Count {
        var newValue: Int = 0
        var previousValue: Int = 0
        
        override fun equals(other: Any?): Boolean {
            return (other is Count) && other.newValue == newValue && other.previousValue == previousValue
        }
        
        override fun hashCode(): Int {
            var result = newValue
            result = 31 * result + previousValue
            return result
        }
    }
}
