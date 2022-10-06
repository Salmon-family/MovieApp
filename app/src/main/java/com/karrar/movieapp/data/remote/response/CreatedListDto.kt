package com.karrar.movieapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class CreatedListDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("favorite_count")
    val favoriteCount: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("iso_639_1")
    val iso6391: String? = null,
    @SerializedName("item_count")
    val itemCount: Int? = null,
    @SerializedName("list_type")
    val listType: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("poster_path")
    val posterPath: Any? = null
)