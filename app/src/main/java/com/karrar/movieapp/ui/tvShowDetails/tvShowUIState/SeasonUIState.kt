package com.karrar.movieapp.ui.tvShowDetails.tvShowUIState

data class SeasonUIState(
    val seasonId: Int = 0,
    val imageUrl: String = "",
    val seasonName: String = "",
    val seasonYear: String = "",
    val seasonNumber: Int = 0,
    val episodeCount: Int = 0,
    val seasonDescription: String = "",
    val episodes: List<EpisodeUIState>,
)
