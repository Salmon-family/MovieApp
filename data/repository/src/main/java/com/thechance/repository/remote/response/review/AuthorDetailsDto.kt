package com.thechance.repository.remote.response.review


import com.google.gson.annotations.SerializedName

data class AuthorDetailsDto(
    @SerializedName("avatar_path")
    val avatarPath: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("username")
    val username: String? = null
)