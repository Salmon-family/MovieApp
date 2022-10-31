package com.thechance.repository.remote.response.tvShow


import com.google.gson.annotations.SerializedName

data class CreatedByDto(
    @SerializedName("credit_id")
    val creditId: String? = null,
    @SerializedName("gender")
    val gender: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("profile_path")
    val profilePath: String? = null
)