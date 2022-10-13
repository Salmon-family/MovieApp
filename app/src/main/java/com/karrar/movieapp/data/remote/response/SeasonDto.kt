package com.karrar.movieapp.data.remote.response


import com.google.gson.annotations.SerializedName
import com.karrar.movieapp.data.remote.response.episode.EpisodeDto

data class SeasonDto(
    @SerializedName("air_date")
    val airDate: String? = null,
    @SerializedName("episode_count")
    val episodeCount: Int? = null,
    @SerializedName("episodes")
    val episodes: List<EpisodeDto>? = null,
    @SerializedName("_id")
    val idString: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("season_number")
    val seasonNumber: Int? = null
)