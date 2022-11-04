package com.thechance.movie.ui.tvShowDetails.tvShowUIState

import com.thechance.movie.utilities.Constants

data class WatchHistoryUIState(
    val id: Int = 0,
    val posterPath: String = "",
    val movieTitle: String = "",
    val movieDuration: Int = 0,
    val voteAverage: String = "",
    val releaseDate: String = "",
    val mediaType: String = Constants.TV_SHOWS
)
