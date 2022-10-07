package com.karrar.movieapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class CreditsDto(
    @SerializedName("cast")
    val cast: List<CastDto>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0
)