package com.karrar.movieapp.domain.models

import com.karrar.movieapp.data.remote.response.SeasonDto

data class TvShowDetails(
    val tvShowId: Int,
    val tvShowImage: String,
    val tvShowName: String,
    val tvShowReleaseDate: String,
    val tvShowGenres: String,
    val tvShowDuration: Int,
    val tvShowReview: Int,
    val tvShowVoteAverage: String,
    val tvShowOverview: String,
    val tvShowSeasons: List<Season>
) : MediaDetails(
    tvShowId,
    tvShowImage,
    tvShowName,
    tvShowReleaseDate,
    tvShowGenres,
    tvShowDuration,
    tvShowReview,
    tvShowVoteAverage,
    tvShowOverview,
    tvShowSeasons
)