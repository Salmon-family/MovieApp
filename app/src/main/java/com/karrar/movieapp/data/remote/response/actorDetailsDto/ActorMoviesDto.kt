package com.karrar.movieapp.data.remote.response.actorDetailsDto


import com.google.gson.annotations.SerializedName

data class ActorMoviesDto(
    @SerializedName("cast")
    val cast: List<Cast?>? = null,
    @SerializedName("crew")
    val crew: List<Crew?>? = null,
    @SerializedName("id")
    val id: Int? = null
)