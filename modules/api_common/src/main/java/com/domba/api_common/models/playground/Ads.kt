package com.domba.api_common.models.playground

data class AdsConfiguration(
    val list: MutableList<Ad>
)

data class Ad(
    val destinationUrl: String?,
    val mediaUrl: MutableList<MediaUrl>?,
    val competitionIds: Array<Int>?
)

data class MediaUrl(
    val height: Int,
    val width: Int,
    val name: String
)