package com.thechance.repository.remote.response


import com.google.gson.annotations.SerializedName
import com.thechance.repository.remote.response.actor.ActorDto

data class CreditsDto(
    @SerializedName("cast")
    val cast: List<ActorDto>? = listOf(),
    @SerializedName("id")
    val id: Int? = 0
)