package com.domba.api_common.models.playground

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Facility(
    val id: Long,
    val fifaId: String?,
    val name: String?,
    val address: String?,
    val place: String?,
    val latitude: Double?,
    val longitude: Double?
): Parcelable