package com.domba.models

import com.google.gson.annotations.SerializedName

data class HttpErrorModel(
    val messages: List<Errors>?,
    val apierror: Apierror?,
    @SerializedName("error_description")
    val errorDescription: String?
)

data class Errors(
    val message: String,
    val severity: String,
    val type: Int,
    val additionalInfo: AdditionalInfo?
)

data class AdditionalInfo(val oldDeviceId: String)
data class Apierror(
    val status: String,
    val message: String,
    val field: String?,
    val timestamp: String
)