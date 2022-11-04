package com.thechance.remote.response.login


import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("status_code")
    val statusCode: Int?,
    @SerializedName("status_message")
    val statusMessage: String?,
    @SerializedName("success")
    val success: Boolean?
)