package com.haretskiy.pavel.gifrandom.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
        @SerializedName("url")
        @Expose
        var url: String? = null,
        @SerializedName("width")
        @Expose
        var width: Int? = null,
        @SerializedName("height")
        @Expose
        var height: Int? = null,
        @SerializedName("size")
        @Expose
        var size: Int? = null
)