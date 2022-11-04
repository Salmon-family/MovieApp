package com.thechance.remote.response.login


import com.google.gson.annotations.SerializedName

data class RequestTokenResponse(
    @SerializedName("expires_at")
    val expiresAt: String?,
    @SerializedName("request_token")
    val requestToken: String?,
    @SerializedName("success")
    val success: Boolean?,
)