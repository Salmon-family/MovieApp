package com.karrar.movieapp.data.remote.response.movieDetailsDto


import com.google.gson.annotations.SerializedName

data class Cast(
    @SerializedName("cast")
    val cast: List<CastX>? = listOf(),
    @SerializedName("crew")
    val crew: List<Crew>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0
)