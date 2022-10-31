package com.thechance.repository.remote.response.tvShow


import com.google.gson.annotations.SerializedName
import com.thechance.repository.remote.response.SeasonDto
import com.thechance.repository.remote.response.SpokenLanguageDto
import com.thechance.repository.remote.response.genre.GenreDto
import java.util.Date

data class TvShowDetailsDto(
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("created_by")
    val createdBy: List<CreatedByDto?>? = null,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int?>? = null,
    @SerializedName("first_air_date")
    val firstAirDate: Date? = null,
    @SerializedName("genres")
    val genres: List<GenreDto?>? = null,
    @SerializedName("homepage")
    val homepage: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("in_production")
    val inProduction: Boolean? = null,
    @SerializedName("languages")
    val languages: List<String?>? = null,
    @SerializedName("last_air_date")
    val lastAirDate: String? = null,
    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAirDto? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("networks")
    val networks: List<NetworkDto?>? = null,
    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: NextEpisodeToAirDto? = null,
    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons")
    val numberOfSeasons: Int? = null,
    @SerializedName("origin_country")
    val originCountry: List<String?>? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_name")
    val originalName: String? = null,
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDto?>? = null,
    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDto?>? = null,
    @SerializedName("seasons")
    val season: List<SeasonDto>? = null,
    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguageDto?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("tagline")
    val tagline: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null
)