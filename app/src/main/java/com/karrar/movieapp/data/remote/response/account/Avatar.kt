package com.karrar.movieapp.data.remote.response.account


import com.google.gson.annotations.SerializedName

data class Avatar(
    @SerializedName("gravatar")
    val gravatar: Gravatar?
)