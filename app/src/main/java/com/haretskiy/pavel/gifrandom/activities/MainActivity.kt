package com.haretskiy.pavel.gifrandom.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.haretskiy.pavel.gifrandom.R
import com.haretskiy.pavel.gifrandom.rest.RestApiImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var d: Disposable

    private val restApi: RestApiImpl by inject()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt.setOnClickListener { doAction() }
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
            d = restApi.loadGifs(limit.toInt(), rating.selectedItem.toString())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                            {
                                Toast.makeText(this, "Data: ${it.data}", Toast.LENGTH_LONG).show()
                            },
                            {
                                Toast.makeText(this, "error: $it", Toast.LENGTH_SHORT).show()
                            }
                    )
        } else {
            Toast.makeText(this, "Add limit. Limit must be less then 25.", Toast.LENGTH_SHORT).show()
        }
    }
}
