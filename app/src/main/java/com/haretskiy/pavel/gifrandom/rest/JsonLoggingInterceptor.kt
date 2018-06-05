package com.haretskiy.pavel.gifrandom.rest

import android.util.Log
import com.haretskiy.pavel.gifrandom.*
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

class JsonLoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response? {
        val request = chain.request()
        try {
            val t1 = System.nanoTime()

            Log.d(START, "Sending request ${request.url()} Headers: ${request.headers()}")

            val response = chain.proceed(request)

            val responseBodyString = response.body()?.string() ?: EMPTY_STRING

            val t2 = System.nanoTime()

            val prettyString = responseBodyString.toPrettyFormat()
            val length = prettyString.length
            if (length > 4000) {
                val chunkCount = length / 4000
                for (i in 0..chunkCount) {
                    val max = 4000 * (i + 1)
                    if (max >= length) {
                        Log.d(RESPONSE, "chunk $i of $chunkCount:")
                        Log.e(RESPONSE, prettyString.substring(4000 * i))
                    } else {
                        Log.d(RESPONSE, "chunk $i of $chunkCount:")
                        Log.e(RESPONSE, prettyString.substring(4000 * i, max))
                    }
                }
            } else {
                Log.e(RESPONSE, prettyString)
            }

            Log.d(END, "Received response for ${response.request().url()} for ${(t2 - t1) / 1e6} milliseconds ")

            val responseBody = response.body()

            return response.newBuilder().body(ResponseBody.create(responseBody?.contentType(), responseBodyString.toByteArray())).build()
        } catch (ex: Exception) {
            return chain.proceed(request)
        }
    }
}