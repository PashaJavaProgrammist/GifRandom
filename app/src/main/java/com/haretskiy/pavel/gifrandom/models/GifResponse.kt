package com.haretskiy.pavel.gifrandom.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GifResponse(

        @SerializedName("data")
        @Expose
        var data: List<Data>? = null
)