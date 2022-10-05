package com.karrar.movieapp.data.remote.response.login


import com.google.gson.annotations.SerializedName

data class LogoutResponse(
    @SerializedName("success")
    val success: Boolean? = null
)