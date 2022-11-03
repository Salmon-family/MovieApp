package com.karrar.movieapp.ui.tvShowDetails.episodes

import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.Error

data class EpisodesUIState(
    val seriesEpisodeUIState: List<EpisodeUIState> = listOf(),
    val isLoading: Boolean = false,
    val error: List<Error> = emptyList()
)
