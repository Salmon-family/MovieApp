package com.karrar.movieapp.data.remote.response.movieDetailsDto.cast


import com.google.gson.annotations.SerializedName

data class GenreDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null
)