package com.domba.api.models

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long
)