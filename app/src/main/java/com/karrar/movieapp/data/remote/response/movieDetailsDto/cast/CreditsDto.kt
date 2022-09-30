package com.karrar.movieapp.data.remote.response.movieDetailsDto.cast


import com.google.gson.annotations.SerializedName

data class CreditsDto(
    @SerializedName("cast")
    val cast: List<CastDto>? = listOf(),
    @SerializedName("crew")
    val crew: List<CrewDto>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0
)