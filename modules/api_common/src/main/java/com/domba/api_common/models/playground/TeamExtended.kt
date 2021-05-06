package com.domba.api_common.models.playground

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Parent(
    val id: Long,
    val fifaId: String?,
    val name: String?,
    val picture: String?,
    val place: String?
): Parcelable