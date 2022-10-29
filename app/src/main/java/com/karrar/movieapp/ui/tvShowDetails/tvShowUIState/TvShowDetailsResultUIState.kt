package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

import com.karrar.movieapp.domain.enums.MediaType

data class TvShowDetailsResultUIState(
    val tvShowId: Int = 0,
    val tvShowImage: String = "",
    val tvShowName: String = "",
    val tvShowReleaseDate: String = "",
    val tvShowSeasonsNumber: Int = 0,
    val tvShowGenres: String = "",
    val tvShowReview: Int = 0,
    val tvShowVoteAverage: String = "",
    val tvShowOverview: String = "",
    val tvShowMediaType: MediaType = MediaType.TV_SHOW
)
