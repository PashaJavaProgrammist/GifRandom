package com.haretskiy.pavel.gifrandom.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Image(
        @SerializedName("url")
        @Expose
        var url: String? = null,
        @SerializedName("width")
        @Expose
        var width: String? = null,
        @SerializedName("height")
        @Expose
        var height: String? = null,
        @SerializedName("size")
        @Expose
        var size: String? = null,
        @SerializedName("mp4")
        @Expose
        var mp4: String? = null,
        @SerializedName("mp4_size")
        @Expose
        var mp4Size: String? = null,
        @SerializedName("webp")
        @Expose
        var webp: String? = null,
        @SerializedName("webp_size")
        @Expose
        var webpSize: String? = null
)