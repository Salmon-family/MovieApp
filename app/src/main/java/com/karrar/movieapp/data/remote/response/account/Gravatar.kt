package com.karrar.movieapp.data.remote.response.account


import com.google.gson.annotations.SerializedName

data class Gravatar(
    @SerializedName("hash")
    val hash: String? = null
)