package com.haretskiy.pavel.gifrandom.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        bt.setOnClickListener({ doAction() })
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }

    //***********************************************************//
    //Methods
    //***********************************************************//

    private fun doAction() {
        val limit = limit.text.toString()
        if (limit.isNotEmpty() && limit.toInt() <= 25) {
            d = getRestApi().loadGifs(APIKEY, limit.toInt(), rating.selectedItem.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe({ Toast.makeText(this, "Data: ${it.data}", Toast.LENGTH_LONG).show() },
                            { Toast.makeText(this, "error: $it", Toast.LENGTH_SHORT).show() })
        } else {
            Toast.makeText(this, "Add limit. Limit must be less then 25.", Toast.LENGTH_SHORT).show()
        }
    }
}
