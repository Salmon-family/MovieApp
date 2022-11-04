package com.thechance.remote.response.actor


import com.google.gson.annotations.SerializedName
import com.thechance.remote.response.MovieDto

data class ActorMoviesDto(
    @SerializedName("cast")
    val cast: List<MovieDto>? = null,
    @SerializedName("id")
    val id: Int? = null
)