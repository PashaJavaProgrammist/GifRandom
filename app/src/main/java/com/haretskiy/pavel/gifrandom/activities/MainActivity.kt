package com.haretskiy.pavel.gifrandom.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.retrofit.getRestApi
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    private var scheduler: Scheduler? = null

    lateinit var d: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        try {
            val d: Disposable =
                    getRestApi().loadGifs(1, "R")
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe({ result -> Log.d("myLogs", "res: ${result.toString()}") },
                                    { error -> Log.d("myLogs", "error: ${error.toString()}") })
        } catch (ex: Exception) {
            Log.d("myLogs", "Exception: ${ex.toString()}")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }
}
