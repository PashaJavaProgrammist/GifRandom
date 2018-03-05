package com.haretskiy.pavel.gifrandom.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class User(
        @SerializedName("avatar_url")
        @Expose
        var avatarUrl: String? = null,
        @SerializedName("banner_url")
        @Expose
        var bannerUrl: String? = null,
        @SerializedName("profile_url")
        @Expose
        var profileUrl: String? = null,
        @SerializedName("username")
        @Expose
        var username: String? = null,
        @SerializedName("display_name")
        @Expose
        var displayName: String? = null,
        @SerializedName("twitter")
        @Expose
        var twitter: String? = null,
        @SerializedName("is_verified")
        @Expose
        var isVerified: Boolean? = null
)