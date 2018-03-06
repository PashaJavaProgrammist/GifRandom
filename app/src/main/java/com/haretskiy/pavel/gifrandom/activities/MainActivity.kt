package com.haretskiy.pavel.gifrandom.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.haretskiy.pavel.gifrandom.APIKEY
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.retrofit.getRestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var d: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt.setOnClickListener({ _ -> doAction() })
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }

    private fun doAction() {
        try {
            d = getRestApi().loadGifs(APIKEY, Integer.parseInt(limit.text.toString()), rating.text.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ result -> Log.d("myLogs", "Data size = ${result.data?.size}") },
                            { error -> Log.d("myLogs", "error: $error") })
        } catch (ex: Exception) {
            Log.d("myLogs", "Exception: $ex")
        }
    }

}
