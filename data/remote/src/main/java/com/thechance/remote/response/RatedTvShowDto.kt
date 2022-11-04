package com.thechance.remote.response

import com.google.gson.annotations.SerializedName

data class RatedTvShowDto(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: Any? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalTitle: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("rating")
    val rating: Float? = null,
    @SerializedName("name")
    val title: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)