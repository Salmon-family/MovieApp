package com.example.models.models

import com.example.models.utilities.MediaType

data class TvShowDetails(
    val tvShowId: Int,
    val tvShowImage: String,
    val tvShowName: String,
    val tvShowReleaseDate: String,
    val tvShowGenres: String,
    val tvShowSeasonsNumber: Int,
    val tvShowReview: Int,
    val tvShowVoteAverage: String,
    val tvShowOverview: String,
    val tvShowSeasons: List<Season>,
    val tvShowType: MediaType
) : MediaDetails(
    tvShowId,
    tvShowImage,
    tvShowName,
    tvShowReleaseDate,
    tvShowGenres,
    tvShowSeasonsNumber,
    tvShowReview,
    tvShowVoteAverage,
    tvShowOverview,
    tvShowSeasons,
    tvShowType
)
