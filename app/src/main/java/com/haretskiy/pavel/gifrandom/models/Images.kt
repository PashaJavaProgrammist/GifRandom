package com.haretskiy.pavel.gifrandom.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Images(

        @SerializedName("original")
        @Expose
        var original: Image? = null,

        @SerializedName("fixed_width")
        @Expose
        var fixed_width: Image? = null,

        @SerializedName("fixed_height")
        @Expose
        var fixed_height: Image? = null
)