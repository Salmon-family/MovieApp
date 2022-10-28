package com.karrar.movieapp.ui.tvShowDetails.episodes

import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.EpisodeUIState
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.SeasonUIState
import com.karrar.movieapp.ui.tvShowDetails.tvShowUIState.Error

data class EpisodesUIState(
    val seriesSeasonUIState: SeasonUIState = SeasonUIState(),
    val seriesEpisodeUIState: List<EpisodeUIState> = listOf(),
    val seriesEpisodeDetails: EpisodeUIState = EpisodeUIState(),
    val isLoading: Boolean = false,
    val error: List<Error> = emptyList()
)
