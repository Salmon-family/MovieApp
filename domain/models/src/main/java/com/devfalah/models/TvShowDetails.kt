package com.devfalah.models

import com.devfalah.types.MediaType

data class TvShowDetails(
    val tvShowId: Int = 0,
    val tvShowImage: String = "",
    val tvShowName: String = "",
    val tvShowReleaseDate: String = "",
    val tvShowGenres: String = "",
    val tvShowSeasonsNumber: Int = 0,
    val tvShowReview: Int = 0,
    val tvShowVoteAverage: String = "",
    val tvShowOverview: String = "",
    val tvShowSeasons: List<Season> = emptyList(),
    val tvShowType :MediaType = MediaType.TV_SHOW
)