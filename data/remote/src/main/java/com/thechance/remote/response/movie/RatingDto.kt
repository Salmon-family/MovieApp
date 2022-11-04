package com.thechance.remote.response.movie


import com.google.gson.annotations.SerializedName

data class RatingDto(
    @SerializedName("status_code")
    val statusCode: Int? = null,
    @SerializedName("status_message")
    val statusMessage: String? = null
)