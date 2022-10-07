package com.karrar.movieapp.data.remote.response.actor


import com.google.gson.annotations.SerializedName
import com.karrar.movieapp.data.remote.response.CastDto

data class ActorMoviesDto(
    @SerializedName("cast")
    val cast: List<CastDto?>? = null,
    @SerializedName("id")
    val id: Int? = null
)