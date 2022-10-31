package com.thechance.repository.remote.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(
    @SerializedName("success")
    val success: Boolean? = null
)