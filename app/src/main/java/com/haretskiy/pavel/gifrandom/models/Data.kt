package com.haretskiy.pavel.gifrandom.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
        @SerializedName("type")
        @Expose
        var type: String? = null,
        @SerializedName("id")
        @Expose
        var id: String? = null,
        @SerializedName("source")
        @Expose
        var source: String? = null,
        @SerializedName("rating")
        @Expose
        var rating: String? = null,
        @SerializedName("images")
        @Expose
        var images: Images? = null,
        @SerializedName("title")
        @Expose
        var title: String? = null
)