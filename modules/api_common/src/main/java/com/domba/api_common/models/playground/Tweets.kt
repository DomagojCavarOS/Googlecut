package com.domba.api_common.models.playground

import com.google.gson.annotations.SerializedName

data class Tweets(
    val id: Long,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("profile_image_url")
    val imageUrl: String?,
    val text: String,
    val entities: Entities
)

data class Entities(val urls: MutableList<Urls>?)
data class Urls(val url: String)